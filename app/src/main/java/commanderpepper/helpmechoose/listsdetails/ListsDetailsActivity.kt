package commanderpepper.helpmechoose.listsdetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import commanderpepper.helpmechoose.R

class ListsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_details_activity)
    }

    companion object {
        const val EXTRA_LIST_ID = "LIST_ID"
    }
}
