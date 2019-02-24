package commanderpepper.helpmechoose.sortlist

import commanderpepper.helpmechoose.BasePresenter
import commanderpepper.helpmechoose.BaseView

interface SortListContract {

    interface View: BaseView<Presenter> {
        fun showOptions(AOption: String, BOption: String)

        fun giveResult(result: String)

        fun showListDetail()
    }

    interface Presenter : BasePresenter {
        fun giveOptions()

        fun saveResult(result: String)
    }
}