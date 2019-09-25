package commanderpepper.helpmechoose.listsdetails

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.addeditlist.AddEditListViewModel
import commanderpepper.helpmechoose.addeditlist.AddEditListViewModelFactory
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.data.model.HMCListsValues
import commanderpepper.helpmechoose.lists.ListsViewModelFactory
import commanderpepper.helpmechoose.sortlist.SortListActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListDetailsFragment : Fragment(), ListsDetailsContract.View,
        CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private lateinit var listsView: ListView
    override lateinit var presenter: ListsDetailsContract.Presenter

    private lateinit var listId: String

    private lateinit var listsDetailsViewModel: ListsDetailsViewModel
    
    private val listAdapter : ListAdapter = ListAdapter(ArrayList(0))

    override fun onResume() {
        super.onResume()
        launch {
//            showList(listsDetailsViewModel.loadList(listId))
            loadList(listsDetailsViewModel.loadListWithScore(listId))
        }
//        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        listId = arguments!!.getString("id")

        val root = inflater.inflate(R.layout.lists_details_fragment, container, false)

        with(root) {
            listsView = this.findViewById(R.id.list_sorted)
            listsView.apply {
                adapter = listAdapter
            }
        }

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_edit_list).apply {
            setImageResource(R.drawable.ic_edit)
            setOnClickListener { Log.d("Unused", "yeah") }
        }.hide()

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_sort_list).apply {
            setImageResource(R.drawable.ic_sort)
            setOnClickListener { showSortLst(listId) }
        }

        val dataSource = HMCListDatabase.getInstance(context!!).hmcDao()

        val listsDetailsViewModelFactory = ListsDetailsViewModelFactory(dataSource)

        listsDetailsViewModel = ViewModelProviders.of(this, listsDetailsViewModelFactory).get(ListsDetailsViewModel::class.java)

        return root
    }

    override fun showList(list: List<String>) {
//        stringAdapter.list = list
    }

    fun loadList(list: List<Pair<String, Int>>) {
        listAdapter.list = list
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

    private class ListAdapter(listOfString: List<Pair<String, Int>>) : BaseAdapter(){
        var list: List<Pair<String, Int>> = listOfString
            set(list) {
                field = list
                notifyDataSetChanged()
            }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val item = getItem(position)

            val rowView = convertView ?: LayoutInflater.from(parent?.context)
                    .inflate(R.layout.value_item_with_score, parent, false)

            with(rowView.findViewById<TextView>(R.id.valueScoreText)) {
                text = item.first
            }

            with(rowView.findViewById<TextView>(R.id.valueScore)) {
                text = item.second.toString()
            }

            return rowView
        }

        override fun getItem(position: Int) = list[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = list.size
    }

    companion object {
        fun newInstance() = ListDetailsFragment()
    }

}