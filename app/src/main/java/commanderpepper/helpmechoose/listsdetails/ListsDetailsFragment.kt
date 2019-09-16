package commanderpepper.helpmechoose.listsdetails

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.model.HMCListsValues
import commanderpepper.helpmechoose.sortlist.SortListActivity

class ListDetailsFragment : Fragment(), ListsDetailsContract.View {

    private lateinit var listsView: ListView
    override lateinit var presenter: ListsDetailsContract.Presenter

    private val stringAdapter : StringAdapter = StringAdapter(ArrayList(0))

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.lists_details_fragment, container, false)

        with (root){
            listsView = this.findViewById(R.id.list_sorted)
            listsView.apply {
                    adapter = stringAdapter
            }
        }

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_edit_list).apply {
            setImageResource(R.drawable.ic_edit)
            setOnClickListener { presenter.openEditList() }
        }.hide()

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_sort_list).apply {
            setImageResource(R.drawable.ic_sort)
            setOnClickListener { presenter.openSortList() }
        }

        return root
    }

    override fun showList(list: List<String>) {
        stringAdapter.list = list
    }

    // Unused for now
    override fun showAddEditList(listId: String) {
        Log.i("Humza", "Filler")
    }

    override fun showSortLst(listId: String) {
        Log.i("Humza", "Filler")
        val intent = Intent(context, SortListActivity::class.java)
        intent.putExtra("taskId", listId)
        startActivity(intent)
    }

    private class StringAdapter(listOfString: List<String>): BaseAdapter() {

        var list: List<String> = listOfString
            set(list) {
                field = list
                notifyDataSetChanged()
            }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val string = getItem(position)

            val rowView = convertView ?: LayoutInflater.from(parent?.context)
                    .inflate(R.layout.value_item, parent, false)

            with(rowView.findViewById<TextView>(R.id.valueText)) {
                text = string
            }

            return rowView
        }

        override fun getItem(position: Int) = list[position]

        override fun getItemId(position: Int)= position.toLong()

        override fun getCount() =  list.size

    }

    companion object {
        fun newInstance() = ListDetailsFragment()
    }

}