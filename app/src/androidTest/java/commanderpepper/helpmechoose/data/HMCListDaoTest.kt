package commanderpepper.helpmechoose.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.data.model.HMCLists
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HMCListDaoTest {

    private lateinit var database: HMCListDatabase

    @Before
    fun initDb() {
        val context = InstrumentationRegistry.getContext()
        database = Room.inMemoryDatabaseBuilder(context,
                HMCListDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun insertHMCListAndGetById() {
        // Insert a HMC List
        database.hmcDao().insertList(DEFAULT_HMCLIST)

        // Retrieve a HMC list by ID from the database
        val loaded = database.hmcDao().getHMCListById(DEFAULT_HMCLIST.id)

        assertHMCList(loaded, DEFAULT_ID, DEFAULT_NAME, DEFAULT_DESCRIPTION)
    }

    private fun assertHMCList(
            hmclist: HMCLists?,
            id: String,
            name: String,
            description: String
    ) {
        assertThat<HMCLists>(hmclist as HMCLists, notNullValue())
        assertThat(hmclist.id, `is`(id))
        assertThat(hmclist.name, `is`(name))
        assertThat(hmclist.description, `is`(description))
    }

    companion object {
        private val DEFAULT_NAME = "name"
        private val DEFAULT_DESCRIPTION = "description"
        private val DEFAULT_ID = "id"

        private val DEFAULT_HMCLIST = HMCLists(DEFAULT_ID, DEFAULT_NAME, DEFAULT_DESCRIPTION)
    }
}