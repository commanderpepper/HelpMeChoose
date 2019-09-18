package commanderpepper.helpmechoose.lists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.data.model.HMCLists
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

class ListsViewModel(hmcListDAO: HMCListDAO,
                     application: Application) :
        AndroidViewModel(application),
        CoroutineScope by CoroutineScope(Dispatchers.Main) {
    private lateinit var hmclist: Flow<HMCLists>

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

}