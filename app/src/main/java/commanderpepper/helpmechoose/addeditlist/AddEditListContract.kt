package commanderpepper.helpmechoose.addeditlist

import commanderpepper.helpmechoose.BasePresenter
import commanderpepper.helpmechoose.BaseView
import commanderpepper.helpmechoose.data.model.HMCLists

interface AddEditListContract {

    interface View : BaseView<Presenter> {

        fun showListAddition()

        fun showHMCLists()

        fun showSnackBar()

    }

    interface Presenter : BasePresenter {

        fun addHMCList(addList: HMCLists)

        fun addHMCListValues(listKeys: String, uuid: String)

        fun saveListToDatabase(addList: HMCLists, listKeys: String, uuid: String)

    }
}