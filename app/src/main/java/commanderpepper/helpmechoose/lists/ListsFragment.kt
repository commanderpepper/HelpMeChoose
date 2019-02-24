package commanderpepper.helpmechoose.lists

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.addeditlist.AddEditListActivity
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.listsdetails.ListsDetailsActivity

class ListsFragment : Fragment(), ListsContract.View {

    override lateinit var presenter: ListsContract.Presenter

    private lateinit var listsView: LinearLayout
    private lateinit var noListsMessageView: LinearLayout

    internal var listListener: ListItemListener = object : ListItemListener {
        override fun onListClick(clickedList: HMCLists) {
            presenter.openListDetails(clickedList)
        }

        override fun onDeleteClick(id: String) {
            presenter.deleteList(id)
        }

    }

    private val listsAdapter: ListAdapter = ListAdapter(ArrayList(0), listListener)

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.lists_fragment, container, false)

        with(root) {
            listsView = this.findViewById(R.id.lists_linearLayout)
            noListsMessageView = this.findViewById(R.id.noHMCLists)
            with(this) {
                this.findViewById<ListView>(R.id.hmc_lists).apply { adapter = listsAdapter }
            }

        }

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_add_task).apply {
            setImageResource(R.drawable.ic_add)
            setOnClickListener { presenter.addList() }
        }

        return root
    }

    // If the list is empty, if the user hasn't made a list then it asks
    override fun showLists(lists: List<HMCLists>) {
        Log.i("Lists Presenter", lists.toString())
//        showNoList()
        if (lists.isEmpty()) {
            showNoList()
        } else {
            listsView.visibility = View.VISIBLE
            noListsMessageView.visibility = View.GONE
            listsAdapter.lists = lists
        }
    }

    // Makes an intent and goes to the activity to make a list
    override fun showAddList() {
        val intent = Intent(context, AddEditListActivity::class.java)
        startActivity(intent)
    }

    override fun showNoList() {
        listsView.visibility = View.GONE
        noListsMessageView.visibility = View.VISIBLE
    }

    /**
     * Goes to a list
     */
    override fun showListDetailsUi(listId: String) {
        val intent = Intent(context, ListsDetailsActivity::class.java).apply {
            putExtra(ListsDetailsActivity.EXTRA_LIST_ID, listId)
        }
        startActivity(intent)
    }

    private class ListAdapter(lists: List<HMCLists>, private val listListener: ListItemListener) : BaseAdapter() {

        var lists: List<HMCLists> = lists
            set(lists) {
                field = lists
                notifyDataSetChanged()
            }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val hmclist = getItem(position)
            val rowView = convertView ?: LayoutInflater.from(parent?.context)
                    .inflate(R.layout.list_item, parent, false)
            val deleteItem: ImageButton = rowView.findViewById(R.id.deleteList)
            deleteItem.setOnClickListener {
                listListener.onDeleteClick(hmclist.id)
            }

            with(rowView.findViewById<TextView>(R.id.listTitle)) {
                text = hmclist.name
            }

            rowView.setOnClickListener {
                listListener.onListClick(hmclist)
            }

            return rowView
        }

        override fun getItem(position: Int) = lists[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = lists.size

    }

    interface ListItemListener {
        fun onListClick(clickedList: HMCLists)

        fun onDeleteClick(id: String)
    }

    companion object {
        fun newInstance() = ListsFragment()
    }
}