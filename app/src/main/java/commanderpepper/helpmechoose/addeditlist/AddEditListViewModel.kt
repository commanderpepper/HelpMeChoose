package commanderpepper.helpmechoose.addeditlist

import androidx.lifecycle.ViewModel
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AddEditListViewModel(val hmcListDAO: HMCListDAO) : ViewModel(),
        CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private fun addHMCList(addList: HMCLists) {
        hmcListDAO.insertList(addList)
    }

    private fun addHMCListValues(listKeys: String, uuid: String) {
        val list = listKeys.split("\n").map { it.trim() }.toSet().toList()
        val hmclist = createHMCValues(list, uuid)
        for (v in hmclist) {
            hmcListDAO.insertValue(v)
        }
    }

    fun saveListToDatabase(addList: HMCLists, listKeys: String, uuid: String) {
        launch(Dispatchers.IO) {
            addHMCList(addList)
            addHMCListValues(listKeys, uuid)
        }
    }

    private fun createHMCValues(list: List<String>, id: String): List<HMCListsValues> {
        return mutableSetOf<HMCListsValues>().apply {
            list.forEach { key1 ->
                list.forEach { key2 ->
                    if (key1 != key2 && !this.contains(HMCListsValues(id, key1, key2, "")) && !this.contains(HMCListsValues(id, key2, key1, ""))) {
                        this.add(HMCListsValues(id, key1, key2, ""))
                    }
                }
            }
        }.toList()
    }


    override fun onCleared() {
        super.onCleared()
        cancel()
    }

}
