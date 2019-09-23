package commanderpepper.helpmechoose.sortlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.listsdetails.ListsDetailsViewModel
import java.lang.IllegalArgumentException

class SortListViewModelFactory(private val id: String, private val datasource: HMCListDAO) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SortListViewModel::class.java)) {
            return SortListViewModel(datasource, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}