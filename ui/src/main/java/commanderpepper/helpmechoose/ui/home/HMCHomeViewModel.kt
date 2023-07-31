package commanderpepper.helpmechoose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commanderpepper.helpmechoose.database.HMCListDataSource
import commanderpepper.helpmechoose.uimodel.HMCItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HMCHomeViewModel(private val hmcListDataSource: HMCListDataSource) : ViewModel() {
    val hmcItemList: StateFlow<HomeUIState> = hmcListDataSource.getHMCLists().map { data ->
        if (data.isNotEmpty())
            HomeUIState.Success(data.map { hmcLists ->
                HMCItem(id = hmcLists.id, name = hmcLists.name)
            })
        else {
            HomeUIState.NoData()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = HomeUIState.Loading
    )

    fun deleteList(listId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            hmcListDataSource.deleteHMCList(listId)
        }
    }

}

sealed class HomeUIState() {
    object Loading : HomeUIState()
    data class Success(val hmcItems: List<HMCItem>) : HomeUIState()
    data class NoData(val message: String = "Add a list!") : HomeUIState()
}