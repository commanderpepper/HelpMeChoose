package commanderpepper.helpmechoose.addeditlist

import commanderpepper.helpmechoose.data.model.HMCLists

class AddEditListPresenter(val addEditListView : AddEditListContract.View) : AddEditListContract.Presenter{

    init {
        addEditListView.presenter = this
    }

    override fun addHMCList(addList: HMCLists) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createTask(){
        addEditListView.showHMCLists()
    }

    private fun updateTask(){
        addEditListView.showHMCLists()
    }

}