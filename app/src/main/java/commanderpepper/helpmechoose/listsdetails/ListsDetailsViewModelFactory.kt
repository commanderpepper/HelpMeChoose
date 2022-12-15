package commanderpepper.helpmechoose.listsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import commanderpepper.helpmechoose.addeditlist.AddEditListViewModel
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import java.lang.IllegalArgumentException

class ListsDetailsViewModelFactory(private val datasource: HMCListDAO) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListsDetailsViewModel::class.java)) {
            return ListsDetailsViewModel(datasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}