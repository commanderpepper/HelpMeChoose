package commanderpepper.helpmechoose.addeditlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.lists.ListsViewModel
import java.lang.IllegalArgumentException

class AddEditListViewModelFactory(private val datasource: HMCListDAO) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditListViewModel::class.java)) {
            return AddEditListViewModel(datasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}