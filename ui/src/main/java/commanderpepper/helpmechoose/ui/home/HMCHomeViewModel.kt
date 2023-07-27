package commanderpepper.helpmechoose.ui.home

import androidx.lifecycle.ViewModel
import commanderpepper.helpmechoose.uimodel.HMCItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HMCHomeViewModel: ViewModel() {
    private val _hmcItemList: MutableStateFlow<List<HMCItem>> = MutableStateFlow(emptyList())
    val hmcItemList: StateFlow<List<HMCItem>> = _hmcItemList
}