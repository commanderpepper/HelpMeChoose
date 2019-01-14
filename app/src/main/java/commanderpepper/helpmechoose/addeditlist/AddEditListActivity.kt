package commanderpepper.helpmechoose.addeditlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import commanderpepper.helpmechoose.R
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

        addEditPresenter = AddEditListPresenter(addEditFragment)
    }
}
