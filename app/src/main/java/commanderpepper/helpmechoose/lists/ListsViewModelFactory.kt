package commanderpepper.helpmechoose.lists

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import java.lang.IllegalArgumentException

class ListsViewModelFactory(private val datasource: HMCListDAO,
                            private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListsViewModel::class.java)) {
            return ListsViewModel(datasource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}