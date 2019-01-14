package commanderpepper.helpmechoose.lists

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.addeditlist.AddEditListActivity
import commanderpepper.helpmechoose.data.model.HMCLists

class ListsFragment : Fragment(), ListsContract.View {

    override lateinit var presenter: ListsContract.Presenter

    private lateinit var listsView: LinearLayout
    private lateinit var noListsMessageView: LinearLayout

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.lists_fragment, container, false)

        with(root) {
            listsView = this.findViewById(R.id.lists_linearLayout)
            noListsMessageView = this.findViewById(R.id.noHMCLists)
        }

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_add_task).apply {
            setImageResource(R.drawable.ic_add)
            setOnClickListener { presenter.addList() }
        }

        return root
    }

    override fun showLists(lists: List<HMCLists>) {
        Log.i("Lists Presenter", lists.toString())
        showNoList()
        if (lists.isEmpty()) {
            showNoList()
        } else {
            listsView.visibility = View.VISIBLE
            noListsMessageView.visibility = View.GONE
        }
    }

    override fun showAddList() {
        val intent = Intent(context, AddEditListActivity::class.java)
        startActivity(intent)
//        startActivityForResult(intent, AddEditListActivity.REQUEST_ADD_LIST)
    }

    override fun showNoList() {
        listsView.visibility = View.GONE
        noListsMessageView.visibility = View.VISIBLE
    }

    override fun showListDetailsUi(listId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance() = ListsFragment()
    }
}