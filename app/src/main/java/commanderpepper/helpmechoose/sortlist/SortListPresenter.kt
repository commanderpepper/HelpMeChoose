package commanderpepper.helpmechoose.sortlist

import android.util.Log
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.*

class SortListPresenter(private val listId: String,
                        val sortListView: SortListContract.View,
                        val hmcListLocalDataSource: HMCListLocalDataSource) : SortListContract.Presenter {

    private lateinit var listOfValue: List<HMCListsValues>
    private var counter = 0

    // Sets this presenter as the instance of the presenter in the view
    init {
        counter = 0
        sortListView.presenter = this
        GlobalScope.launch(Dispatchers.IO) {
            listOfValue = hmcListLocalDataSource.hmclistdao.getHMCListsValues(listId)
        }
    }

    override fun giveOptions() {
        GlobalScope.launch {
            val optionA = listOfValue[counter].key1
            val optionB = listOfValue[counter].key2
            withContext(Dispatchers.Main) {
                sortListView.showOptions(optionA, optionB)
            }
        }
    }

    override fun saveResult(result: String) {
        GlobalScope.launch {
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