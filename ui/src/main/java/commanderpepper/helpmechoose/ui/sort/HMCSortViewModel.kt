package commanderpepper.helpmechoose.ui.sort

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commanderpepper.helpmechoose.database.HMCListDataSource
import commanderpepper.helpmechoose.database.model.HMCListsValues
import commanderpepper.helpmechoose.database.model.Relation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HMCSortViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val hmcListDataSource: HMCListDataSource
): ViewModel() {
    private val listId: String = savedStateHandle["listId"]!!
    private val hmcListValues = hmcListDataSource.getHMCListsValues(listId)
    private var indexToPresent = MutableStateFlow(0)

    val hmcSortUIState : StateFlow<HMCSortUIState> = hmcListValues.combine(indexToPresent){ hmcListValues,  index ->
        if(hmcListValues.isNotEmpty() && index < hmcListValues.size){
            val hmcListItem = hmcListValues[index]
            HMCSortUIState.Success(hmcSortUI = HMCSortUI(listId = listId, keyOne = hmcListItem.key1, keyTwo = hmcListItem.key2))
        }
        else if(index == hmcListValues.size){
            HMCSortUIState.DoneSorting
        }
        else {
            HMCSortUIState.Error
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L), HMCSortUIState.Loading)

    fun setRelationship(keyOne: String, keyTwo: String, relation: Relation){
        viewModelScope.launch(Dispatchers.IO) {
            hmcListDataSource.insertValue(
                HMCListsValues(
                    id = listId,
                    key1 = keyOne,
                    key2 = keyTwo,
                    relation = relation
                )
            )
            withContext(Dispatchers.Main){
                indexToPresent.value = indexToPresent.value + 1
            }
        }
    }
}

data class HMCSortUI(val listId: String, val keyOne: String, val keyTwo: String)

sealed class HMCSortUIState(){
    object Loading: HMCSortUIState()
    object Error: HMCSortUIState()
    object DoneSorting: HMCSortUIState()
    data class Success(val hmcSortUI: HMCSortUI): HMCSortUIState()

}