package commanderpepper.helpmechoose.lists

import android.util.Log
import commanderpepper.helpmechoose.data.HMCListRepository
import commanderpepper.helpmechoose.data.model.HMCList
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.util.launchCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ListsPresenter(val listView: ListsContract.View,
                     val hmcListRepository: HMCListRepository) : ListsContract.Presenter {

    init {
        listView.presenter = this
    }

    override fun loadLists() {
        GlobalScope.launch(Dispatchers.IO) {
            val lists = async { hmcListRepository.getLists() }
            withContext(Dispatchers.Main) {
                listView.showLists(lists.await())
            }
        }
    }

    override fun addList() {
        Log.i("Lists Presenter", "Inside load lists")
        listView.showAddList()
    }

    override fun start() {
        loadLists()
    }

    override fun openListDetails(requestedList: HMCLists) {
        Log.i("Lists Presenter", "Inside load lists")
    }


}