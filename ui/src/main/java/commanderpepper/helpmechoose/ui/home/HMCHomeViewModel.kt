package commanderpepper.helpmechoose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commanderpepper.helpmechoose.database.HMCListDataSource
import commanderpepper.helpmechoose.uimodel.HMCItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HMCHomeViewModel(hmcListDataSource: HMCListDataSource): ViewModel() {
    val hmcItemList: StateFlow<List<HMCItem>> = hmcListDataSource.getHMCLists().map {
        it.map { hmcLists ->
            HMCItem(id = hmcLists.id, name = hmcLists.name)
        }
    }.stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5_000L), initialValue = emptyList())

}