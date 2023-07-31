package commanderpepper.helpmechoose.ui.newlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commanderpepper.helpmechoose.database.HMCListDataSource
import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import commanderpepper.helpmechoose.database.model.Relation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class HMCNewListViewModel(private val hmcListDataSource: HMCListDataSource): ViewModel() {

    private val _newListUIState: MutableStateFlow<NewListUIState> = MutableStateFlow(NewListUIState())
    val newListUIState: StateFlow<NewListUIState> = _newListUIState

    private val _saveToDbState: MutableStateFlow<SaveToDBStates> = MutableStateFlow(SaveToDBStates.NoAction)
    val saveToDBStates: StateFlow<SaveToDBStates> = _saveToDbState

    fun addName(newName: String){
        if(newName.isNotEmpty()){
            viewModelScope.launch {
                _newListUIState.emit(_newListUIState.value.copy(name = newName))
            }
        }
    }

    fun addItem(item: String){
        if(item.isNotEmpty()){
            viewModelScope.launch {
                val updatedList = _newListUIState.value.values + item
                _newListUIState.emit(_newListUIState.value.copy(values = updatedList))
            }
        }
    }

    fun saveToDB() {
        if(_newListUIState.value.isValid()){
            // save
            viewModelScope.launch {
                createNewList(listId = UUID.randomUUID().toString(), listName = _newListUIState.value.name, listValues = _newListUIState.value.values)
                _saveToDbState.emit(SaveToDBStates.Success)
            }
        }
        else {
            viewModelScope.launch {
                val message = when {
                    _newListUIState.value.name.isEmpty() && _newListUIState.value.name.isEmpty() -> "You've got no name for your list and no items either"
                    _newListUIState.value.name.isEmpty() -> "You've got no name for your list"
                    _newListUIState.value.values.isEmpty() -> "You've got no items in your list"
                    _newListUIState.value.values.size <= 1 -> "That's hardly a list"
                    else -> "The list is not valid"
                }
                _saveToDbState.emit(SaveToDBStates.Error(message = message))
            }
        }
    }

    fun dismissClicked(){
        viewModelScope.launch {
            _saveToDbState.emit(SaveToDBStates.NoAction)
        }
    }

    private suspend fun createNewList(listId: String, listName: String, listValues: List<String>) = withContext(Dispatchers.IO) {
        hmcListDataSource.saveHMCList(hmcList = HMCLists(id = listId, name = listName))
        val allPossiblePairs = mutableListOf<Pair<String, String>>()
        for(i in 0 until listValues.size - 1){
            for(j in i + 1 until listValues.size){
                allPossiblePairs.add(listValues[i] to listValues[j])
            }
        }
        allPossiblePairs.map { (keyOne, keyTwo) ->
            async {
                hmcListDataSource.insertValue(HMCListsValues(id = listId, key1 = keyOne, key2 = keyTwo, relation = Relation.EQUAL))
            }
        }.awaitAll()
    }

    fun removeItem(item: String) {
        viewModelScope.launch {
            val list = _newListUIState.value.values.toMutableList()
            list.remove(item)
            _newListUIState.value = _newListUIState.value.copy(values = list)
        }
    }
}

data class NewListUIState(
    val name: String = "",
    val values: List<String> = emptyList()
)

fun NewListUIState.isValid(): Boolean {
    return name.isNotEmpty() && values.isNotEmpty() && values.size > 1
}

sealed class SaveToDBStates {
    object NoAction: SaveToDBStates()
    object Success: SaveToDBStates()
    data class Error(val message: String): SaveToDBStates()
}