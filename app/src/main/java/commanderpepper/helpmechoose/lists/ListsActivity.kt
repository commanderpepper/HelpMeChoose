package commanderpepper.helpmechoose.lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.util.replaceFragmentInActivity

class ListsActivity : AppCompatActivity() {

    private lateinit var listsPresenter: ListsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_activity)

        // Set up the Fragment
        val listsFragment: ListsFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as ListsFragment? ?: ListsFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        // Set up the database
        val database = HMCListDatabase

        // Set up the HMC Lists Repository
        val hmcListLocalDataSource = HMCListLocalDataSource.getInstance(database.getInstance(this.applicationContext).hmcDao())

        // Set up the presenter
        listsPresenter = ListsPresenter(listsFragment, hmcListLocalDataSource)

    }
}
