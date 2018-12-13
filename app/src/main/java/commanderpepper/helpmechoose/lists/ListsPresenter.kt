package commanderpepper.helpmechoose.lists

import android.util.Log
import commanderpepper.helpmechoose.data.model.HMCList
import commanderpepper.helpmechoose.util.launchCoroutine

class ListsPresenter (val listView : ListsContract.View) : ListsContract.Presenter {

    init {
        listView.presenter = this
    }

    override fun loadLists() = launchCoroutine {
        Log.i("Lists Presenter", "Inside load lists")
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