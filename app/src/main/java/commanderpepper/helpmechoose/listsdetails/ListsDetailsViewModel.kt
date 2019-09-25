package commanderpepper.helpmechoose.listsdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.*

class ListsDetailsViewModel(val hmcListDAO: HMCListDAO) : ViewModel(),
        CoroutineScope by CoroutineScope(Dispatchers.Main) {

    suspend fun loadList(listId: String) : List<String> {
        return async {
            val list = withContext(Dispatchers.IO) { hmcListDAO.getHMCListsValues(listId) }
            val matrix = withContext(Dispatchers.Default) { makeMapFromHMCValues(list) }
            return@async withContext(Dispatchers.Default) { makeSortedListFromMatrix(matrix) }
        }.await()
    }

    suspend fun loadListWithScore(listId: String) : List<Pair<String, Int>> {
        return async {
            val list = withContext(Dispatchers.IO) { hmcListDAO.getHMCListsValues(listId) }
            val matrix = withContext(Dispatchers.Default) { makeMapFromHMCValues(list) }
            return@async withContext(Dispatchers.Default) { makeSortedList(matrix) }
        }.await()
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

    // Makes a sorted list from the database
    private fun makeSortedList(matrix: MutableMap<Pair<String, String>, String>): List<Pair<String, Int>> {
        val sortedList = mutableListOf<Pair<String, Int>>()
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
                .sortedByDescending { (key, value) -> value }.forEach { sortedList.add(it.first to it.second) }

        return sortedList.toList()
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }
}
