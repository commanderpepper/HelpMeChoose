package commanderpepper.helpmechoose.listsdetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.util.replaceFragmentInActivity

class ListsDetailsActivity : AppCompatActivity() {

    private lateinit var listsDetailsPresenter: ListsDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_details_activity)

        // Get the requested task id
        val taskId = intent.getStringExtra(EXTRA_LIST_ID)
        Log.i("Humza", "$taskId")

        //Set up the fragment
        val fragment: ListDetailsFragment = supportFragmentManager.findFragmentById(R.id.contentFrameListDetail)
                as ListDetailsFragment? ?: ListDetailsFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrameListDetail)
        }

        // Set up the database
        val database = HMCListDatabase

        // Set up the HMC Lists Repository
        val hmcListLocalDataSource = HMCListLocalDataSource.getInstance(database.getInstance(this.applicationContext).hmcDao())

        listsDetailsPresenter = ListsDetailsPresenter(listId = taskId, detailView = fragment, hmcListLocalDataSource = hmcListLocalDataSource)
    }

    companion object {
        const val EXTRA_LIST_ID = "LIST_ID"
    }
}
