package commanderpepper.helpmechoose.addeditlist
import commanderpepper.helpmechoose.BasePresenter
import commanderpepper.helpmechoose.BaseView
import commanderpepper.helpmechoose.data.model.HMCList

interface AddEditListContract{
    interface View : BaseView<Presenter> {

        fun showListAddition()

    }

    interface Presenter : BasePresenter {

        fun addHMCList(addList : HMCList)

    }
}