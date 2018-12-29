package commanderpepper.helpmechoose.lists

import android.util.Log
import commanderpepper.helpmechoose.data.model.HMCList
import commanderpepper.helpmechoose.util.launchCoroutine
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.CoroutineContext

class ListsPresenter(val listView: ListsContract.View) : ListsContract.Presenter {

    init {
        listView.presenter = this
    }

//    override fun loadLists() = launchCoroutine(Dispatchers.IO){
//        Log.i("Lists Presenter", "Inside load lists")
//        Log.i("Lists Presenter", this.coroutineContext.toString())
//    }

    override fun loadLists() {
        GlobalScope.launch(Dispatchers.IO) {
            val lists = listOf<HMCList>()
            withContext(Dispatchers.Main) {
                listView.showLists(lists)
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

    override fun openListDetails(requestedList: HMCList) {
        Log.i("Lists Presenter", "Inside load lists")
    }


}