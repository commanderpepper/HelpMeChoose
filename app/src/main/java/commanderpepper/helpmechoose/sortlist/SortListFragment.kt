package commanderpepper.helpmechoose.sortlist

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import commanderpepper.helpmechoose.R

class SortListFragment : Fragment(), SortListContract.View {
    override lateinit var presenter: SortListContract.Presenter

    private lateinit var optionA: Button
    private lateinit var optionB: Button
    private lateinit var neither: Button


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.sort_list_fragment, container, false)

        with(root) {
            optionA = findViewById(R.id.optionA)
            optionB = findViewById(R.id.optionB)
            neither = findViewById(R.id.neitherOption)

            optionA.setOnClickListener {
                giveResult(">")
            }

            optionB.setOnClickListener {
                giveResult("<")
            }

            neither.setOnClickListener {
                giveResult("=")
            }
        }
        return root
    }

    override fun showOptions(AOption: String, BOption: String) {
        optionA.text = AOption
        optionB.text = BOption
    }

    override fun giveResult(result: String) {
        presenter.saveResult(result)
    }

    override fun showListDetail() {
        with(activity) {
            this!!.setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        fun newInstance() = SortListFragment()
    }
}
