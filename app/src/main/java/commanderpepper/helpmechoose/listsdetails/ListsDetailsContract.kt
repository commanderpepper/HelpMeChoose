package commanderpepper.helpmechoose.listsdetails

import commanderpepper.helpmechoose.BasePresenter
import commanderpepper.helpmechoose.BaseView
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.data.model.HMCListsValues

interface ListsDetailsContract {

    interface View : BaseView<Presenter> {
        fun showList(list: List<String>)

        fun showAddEditList(listId: String)

        fun showSortLst(listId: String)
    }

    interface Presenter : BasePresenter {
        fun loadList()

        fun openSortList()

        fun openEditList()
    }
}