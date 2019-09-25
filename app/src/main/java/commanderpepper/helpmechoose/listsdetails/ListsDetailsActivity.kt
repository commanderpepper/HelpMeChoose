package commanderpepper.helpmechoose.listsdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.util.replaceFragmentInActivity

class ListsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_details_activity)

        // Get the requested task id
        val taskId = intent.getStringExtra(EXTRA_LIST_ID)
        Log.i("Humza", "$taskId")

        val bundle = Bundle()
        bundle.putString("id", taskId)

        //Set up the fragment
        val fragment: ListDetailsFragment = supportFragmentManager.findFragmentById(R.id.contentFrameListDetail)
                as ListDetailsFragment? ?: ListDetailsFragment.newInstance().also {
            it.arguments = bundle
            replaceFragmentInActivity(it, R.id.contentFrameListDetail)
        }
    }

    companion object {
        const val EXTRA_LIST_ID = "LIST_ID"
    }
}
