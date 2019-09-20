package commanderpepper.helpmechoose.sortlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.util.replaceFragmentInActivity

class SortListActivity : AppCompatActivity() {

    private lateinit var sortListPresenter: SortListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sort_list_activity)

        // Get the requested task id
        val taskId = intent.getStringExtra("taskId")
        Log.i("Humza", "$taskId")

        val sortListFragment: SortListFragment = supportFragmentManager.findFragmentById(R.id.contentFrameSortList)
                as SortListFragment? ?: SortListFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrameSortList)
        }

        // Set up the database
        val database = HMCListDatabase

        // Set up the HMC Lists Repository
        val hmcListLocalDataSource = HMCListLocalDataSource.getInstance(database.getInstance(this.applicationContext).hmcDao())

        sortListPresenter = SortListPresenter(taskId, sortListFragment, hmcListLocalDataSource)
    }
}
