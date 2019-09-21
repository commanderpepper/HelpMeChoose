package commanderpepper.helpmechoose.lists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.data.model.HMCLists
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ListsViewModel(val hmcListDAO: HMCListDAO,
                     application: Application) :
        AndroidViewModel(application),
        CoroutineScope by CoroutineScope(Dispatchers.Main) {

    val hmclist: Flow<List<HMCLists>> = hmcListDAO.getFlowHMCLists().flowOn(Dispatchers.IO)

    fun deleteList(listId: String) {
        launch(Dispatchers.IO) {
            hmcListDAO.deleteHMCListById(listId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }
}

