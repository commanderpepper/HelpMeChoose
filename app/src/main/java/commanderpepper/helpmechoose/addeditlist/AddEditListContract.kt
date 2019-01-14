package commanderpepper.helpmechoose.addeditlist

import commanderpepper.helpmechoose.BasePresenter
import commanderpepper.helpmechoose.BaseView
import commanderpepper.helpmechoose.data.model.HMCLists

interface AddEditListContract {

    interface View : BaseView<Presenter> {

        fun showListAddition()

        fun showHMCLists()

    }

    interface Presenter : BasePresenter {

        fun addHMCList(addList: HMCLists)

    }
}