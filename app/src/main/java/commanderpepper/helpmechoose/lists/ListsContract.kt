package commanderpepper.helpmechoose.lists

import commanderpepper.helpmechoose.BasePresenter
import commanderpepper.helpmechoose.BaseView
import commanderpepper.helpmechoose.data.model.HMCList

interface ListsContract {

    interface View : BaseView<Presenter> {

        fun showLists()

        fun showAddList()

        fun showNoList()

        fun showListDetailsUi(listId: String)

    }

    interface Presenter : BasePresenter {

        fun loadLists()

        fun addList()

        fun openListDetails(requestedList : HMCList)

    }

}