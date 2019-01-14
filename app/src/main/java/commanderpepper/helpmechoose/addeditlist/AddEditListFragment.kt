package commanderpepper.helpmechoose.addeditlist

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.model.HMCLists
import java.util.*

class AddEditListFragment : Fragment(), AddEditListContract.View {

    override lateinit var presenter: AddEditListContract.Presenter

    lateinit var title: TextView
    lateinit var description: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.add_edit_list_fragment, container, false)

        title = root.findViewById(R.id.add_list_title)
        description = root.findViewById(R.id.add_list_desc)

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_save_task).apply {
            setImageResource(R.drawable.ic_save)
            setOnClickListener { addHMCList(title.text.toString(), description.text.toString()) }
        }

        return root
    }

    override fun showListAddition() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showHMCLists() {
        with(activity) {
            this!!.setResult(Activity.RESULT_OK)
            finish()
        }
    }


    // TODO make a method to call the presenter, this method will pass a HMC Lists to the presenter to create something
    private fun addHMCList(name: String, description: String) {
        val hmcList = HMCLists(UUID.randomUUID().toString(), name)
        presenter.addHMCList(hmcList)
    }


    companion object {
        fun newInstance() = AddEditListFragment()
    }
}