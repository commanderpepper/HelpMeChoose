package commanderpepper.helpmechoose.addeditlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.util.replaceFragmentInActivity

class AddEditListActivity : AppCompatActivity() {

    private lateinit var addEditPresenter: AddEditListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_list_activity)

        val addEditFragment = supportFragmentManager.findFragmentById(R.id.contentFrameAddEdit)
                as AddEditListFragment? ?: AddEditListFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrameAddEdit)
        }

        // Set up the database
        val database = HMCListDatabase

        // Sets up the local data source
        val dataSource = HMCListLocalDataSource.getInstance(database.getInstance(this.applicationContext).hmcDao())

        addEditPresenter = AddEditListPresenter(addEditFragment, dataSource)
    }
}
