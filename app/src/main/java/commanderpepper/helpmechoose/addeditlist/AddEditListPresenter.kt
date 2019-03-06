package commanderpepper.helpmechoose.addeditlist

import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import java.util.Arrays.asList


class AddEditListPresenter(val addEditListView: AddEditListContract.View,
                           val hmcDataSource: HMCListLocalDataSource) : AddEditListContract.Presenter {

    init {
        addEditListView.presenter = this
    }

    override fun addHMCList(addList: HMCLists) {
        hmcDataSource.hmclistdao.insertList(addList)
    }

    // I don't need to initialize anything I think
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Save the info to the database
    override fun addHMCListValues(listKeys: String, uuid: String) {
        val list = listKeys.split("\n").map { it.trim() }.toSet().toList()
        val hmclist = createHMCValues(list, uuid)
        for (v in hmclist) {
            hmcDataSource.hmclistdao.insertValue(v)
        }
    }

    // Save the hmc list and hmc list values to the database
    override fun saveListToDatabase(addList: HMCLists, listKeys: String, uuid: String) {
        if (addList.name == "" || listKeys.split("\n").size <= 1 || listKeys.isEmpty()) {
            addEditListView.showSnackBar()
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                addHMCList(addList)
                addHMCListValues(listKeys, uuid)
            }
            addEditListView.showHMCLists()
        }
    }

    /**
     * Used in the initial creation of a list
     * Each value will be set to ""
     * I guess I need to pass an ID for the id
     */
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

    private fun createTask() {
        addEditListView.showHMCLists()
    }

    private fun updateTask() {
        addEditListView.showHMCLists()
    }

}