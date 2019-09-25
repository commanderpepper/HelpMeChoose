package commanderpepper.helpmechoose.addeditlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.util.replaceFragmentInActivity

class AddEditListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_list_activity)

        val addEditFragment = supportFragmentManager.findFragmentById(R.id.contentFrameAddEdit)
                as AddEditListFragment? ?: AddEditListFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrameAddEdit)
        }
    }
}
