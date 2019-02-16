package commanderpepper.helpmechoose.lists

import commanderpepper.helpmechoose.BasePresenter
import commanderpepper.helpmechoose.BaseView
import commanderpepper.helpmechoose.data.model.HMCLists

interface ListsContract {

    interface View : BaseView<Presenter> {

        fun showLists(lists : List<HMCLists>)

        fun showAddList()

        fun showNoList()

        fun showListDetailsUi(listId: String)

    }

    interface Presenter : BasePresenter {

        fun loadLists()

        fun addList()

        fun openListDetails(requestedList : HMCLists)

        fun deleteList(listId: String)

    }

}