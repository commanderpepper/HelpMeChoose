package commanderpepper.helpmechoose.lists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import commanderpepper.helpmechoose.R
import commanderpepper.helpmechoose.util.replaceFragmentInActivity

class ListsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_activity)

        // Set up the Fragment
        val listsFragment: ListsFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as ListsFragment? ?: ListsFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }
    }
}
