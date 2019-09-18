package commanderpepper.helpmechoose.lists

import android.util.Log
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.model.HMCLists
import kotlinx.coroutines.*

class ListsPresenter(val listView: ListsContract.View,
                     val hmcListLocalDataSource: HMCListLocalDataSource) :
        ListsContract.Presenter, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    // Sets this presenter as the instance of the presenter in the view
    init {
        listView.presenter = this
    }

    /**
     * Retrieves the a list of hmc list from the database and passes that info to the view
     */
    override fun loadLists() {
        launch(Dispatchers.IO) {
            val lists = async { hmcListLocalDataSource.getHMCLists() }
            withContext(Dispatchers.Main) {
                listView.showLists(lists.await())
            }
        }
    }

    /**
     * Calls the view's show add list which launches an activity
     */
    override fun addList() {
        Log.i("Lists Presenter", "Inside load lists")
        listView.showAddList()
    }

    /**
     * calls the loadlists() presenter method when the activity starts
     */
    override fun start() {
        loadLists()
    }

    /**
     * called from the view, tells the view to open an activity when a specific id
     */
    override fun openListDetails(requestedList: HMCLists) {
        Log.i("Lists Presenter", "Inside load lists")
        listView.showListDetailsUi(requestedList.id)
    }

    /**
     * Delete an item in the database
     */
    override fun deleteList(listId: String) {
        launch(Dispatchers.IO) {
            hmcListLocalDataSource.deleteHMCList(listId)
            withContext(Dispatchers.Main) {
                this@ListsPresenter.loadLists()
            }
        }
    }


}