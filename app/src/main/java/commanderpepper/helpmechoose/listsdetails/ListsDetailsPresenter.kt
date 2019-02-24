package commanderpepper.helpmechoose.listsdetails

import android.util.Log
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.*

class ListsDetailsPresenter(
        private val listId: String,
        val detailView: ListsDetailsContract.View,
        val hmcListLocalDataSource: HMCListLocalDataSource
) : ListsDetailsContract.Presenter {

    // Sets this presenter as the instance of the presenter in the view
    init {
        detailView.presenter = this
    }

    override fun start() {
        loadList()
    }

    // Retrieve a list that is sorted and gives that list to the fragment
    override fun loadList() {
        Log.i("Lists Details Presenter", "Inside load list")
        GlobalScope.launch(Dispatchers.IO) {
            val list = async { hmcListLocalDataSource.getHMCListsValues(listId) }.await()
            val matrix = async { makeMapFromHMCValues(list) }.await()
            val sortedList = async { makeSortedListFromMatrix(matrix) }.await()
            Log.i("Humza", "$list")
            Log.i("Humza", "$matrix")
            Log.i("Humza", "$sortedList")
            withContext(Dispatchers.Main){
                detailView.showList(sortedList)
            }

        }
    }

    override fun openSortList() {
        Log.i("Humza", "Work in Progress")
        detailView.showSortLst(listId)
    }

    // Unused for now
    override fun openEditList() {
        Log.i("Humza", "Work in Progress")
    }

    /**
     * Create a matrix from a list of HMC Lists Values
     */
    private fun makeMapFromHMCValues(list: List<HMCListsValues>): MutableMap<Pair<String, String>, String> {
        return mutableMapOf<Pair<String, String>, String>().apply {
            list.forEach {
                this[it.key1 to it.key2] = it.value
                this[it.key2 to it.key1] = when (it.value) {
                    ">" -> "<"
                    "<" -> ">"
                    else -> "="
                }
            }
        }
    }

    // Makes a sorted list from the database
    private fun makeSortedListFromMatrix(matrix: MutableMap<Pair<String, String>, String>): List<String> {
        val sortedList = mutableListOf<String>()
        val rankingsMap = mutableMapOf<String, Int>()

        matrix.forEach {
            val key = it.key.first
            when (it.value) {
                ">" -> if (rankingsMap[key] != null) rankingsMap[key] = rankingsMap[key]!! + 3 else rankingsMap[key] = 3
                "<" -> if (rankingsMap[key] != null) rankingsMap[key] = rankingsMap[key]!! + 2 else rankingsMap[key] = 2
                else -> if (rankingsMap[key] != null) rankingsMap[key] = rankingsMap[key]!! + 1 else rankingsMap[key] = 1
            }
        }

        rankingsMap.toList()
                .sortedByDescending { (key, value) -> value }.forEach { sortedList.add(it.first) }

        return sortedList.toList()
    }
}
