package commanderpepper.helpmechoose.sortlist

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.listsdetails.ListsDetailsViewModel
import commanderpepper.helpmechoose.listsdetails.ListsDetailsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SortListFragment :
        Fragment(),
        SortListContract.View,
        CoroutineScope by CoroutineScope(Dispatchers.Main) {
    override lateinit var presenter: SortListContract.Presenter

    private lateinit var optionA: TextView
    private lateinit var optionB: TextView
    private lateinit var neither: Button

    private lateinit var listId: String

    private lateinit var sortListViewModel: SortListViewModel

    private var state = false


    override fun onResume() {
        super.onResume()
//        presenter.start()
    }

    // Sets up the root so the buttons views can be created and set their behavior
    @ObsoleteCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.sort_list_fragment, container, false)

        val dataSource = HMCListDatabase.getInstance(context!!).hmcDao()

        listId = arguments!!.getString("id")

        val sortListsDetailsViewModelFactory = SortListViewModelFactory(listId, dataSource)

        sortListViewModel = ViewModelProviders.of(this, sortListsDetailsViewModelFactory).get(SortListViewModel::class.java)

        with(root) {
            optionA = findViewById(R.id.optionA)
            optionB = findViewById(R.id.optionB)
            neither = findViewById(R.id.neitherOption)

            launch {
                sortListViewModel.optionA.collect {
                    optionA.text = it
                }
                sortListViewModel.optionB.collect {
                    optionB.text = it
                }
            }

            optionA.setOnClickListener {
                sortListViewModel.saveResult(">")
//                giveResult(">")
                checkState()
            }

            optionB.setOnClickListener {
                sortListViewModel.saveResult("<")
//                giveResult("<")
                checkState()
            }

            neither.setOnClickListener {
                sortListViewModel.saveResult("=")
//                giveResult("=")
                checkState()
            }
        }
        return root
    }

    @ObsoleteCoroutinesApi
    fun checkState(){
        launch(Dispatchers.Default){
            sortListViewModel.listStateFlow.collect {
                Log.i("Humza", "State: $it")
                if(!it){
                    Log.i("Humza", "We should be finished")
                    showListDetail()
                }
            }
        }
    }

    override fun showOptions(AOption: String, BOption: String) {
        optionA.text = AOption
        optionB.text = BOption
    }

    override fun giveResult(result: String) {
        presenter.saveResult(result)
    }

    // Go back to the detail list view
    override fun showListDetail() {
        Log.i("Humza", "Being called?")
        with(activity) {
            this!!.setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        fun newInstance() = SortListFragment()
    }
}
