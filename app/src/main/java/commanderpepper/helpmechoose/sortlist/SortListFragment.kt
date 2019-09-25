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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class SortListFragment :
        Fragment(),
        CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private lateinit var optionA: TextView
    private lateinit var optionB: TextView
    private lateinit var neither: Button

    private lateinit var listId: String

    private lateinit var sortListViewModel: SortListViewModel

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

            optionA.setOnClickListener {
                sortListViewModel.saveResult(">")
                checkAndSetText()
            }

            optionB.setOnClickListener {
                sortListViewModel.saveResult("<")
                checkAndSetText()
            }

            neither.setOnClickListener {
                sortListViewModel.saveResult("=")
                checkAndSetText()
            }
        }

        setText()

        return root
    }

    private fun SortListFragment.checkAndSetText() {
        checkState()
        setText()
    }

    @ObsoleteCoroutinesApi
    fun checkState() {
        launch(Dispatchers.Default) {
            sortListViewModel.listStateFlow.collect {
                Log.i("Humza", "State: $it")
                if (!it) {
                    Log.i("Humza", "We should be finished")
                    withContext(Dispatchers.Main) {
                        showListDetail()
                    }
                }
            }
        }
    }

    fun setText() {
        launch {
            sortListViewModel.optionA.collect {
                optionA.text = it
            }
            sortListViewModel.optionB.collect {
                optionB.text = it
            }
        }
    }

    // Go back to the detail list view
    fun showListDetail() {
        Log.i("Humza", "Being called?")
        with(activity) {
            if (activity != null) {
                this!!.setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    companion object {
        fun newInstance() = SortListFragment()
    }
}
