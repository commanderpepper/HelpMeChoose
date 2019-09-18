package commanderpepper.helpmechoose.sortlist

import android.util.Log
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.*

class SortListPresenter(private val listId: String,
                        val sortListView: SortListContract.View,
                        val hmcListLocalDataSource: HMCListLocalDataSource)
    : SortListContract.Presenter, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private lateinit var listOfValue: List<HMCListsValues>
    private var counter = 0

    // Sets this presenter as the instance of the presenter in the view
    init {
        counter = 0
        sortListView.presenter = this
        launch(Dispatchers.IO) {
            listOfValue = hmcListLocalDataSource.hmclistdao.getHMCListsValues(listId)
        }
    }

    // Retrieve the keys using the counter and passes it to the fragment
    override fun giveOptions() {
        launch (Dispatchers.IO){
            val optionA = listOfValue[counter].key1
            val optionB = listOfValue[counter].key2
            withContext(Dispatchers.Main) {
                sortListView.showOptions(optionA, optionB)
            }
        }
    }

    /**
     * Save the result from the method
     * A lot is being done in the method like saving a counter so i think this maybe need to be countdown some
     */
    override fun saveResult(result: String) {
        launch(Dispatchers.IO) {
            Log.i("Humza", "$counter")
            val value = listOfValue[counter]
            value.value = result
            hmcListLocalDataSource.hmclistdao.insertValue(value)
            counter += 1
            Log.i("Humza", "$counter")
            if (counter >= listOfValue.size) {
                withContext(Dispatchers.Main) { sortListView.showListDetail() }
            } else {
                withContext(Dispatchers.Main) { giveOptions() }
            }
        }
    }

    override fun start() {
        giveOptions()
    }

}