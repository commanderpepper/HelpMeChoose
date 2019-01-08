package commanderpepper.helpmechoose.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.data.HMCListLocalDataSource
import commanderpepper.helpmechoose.util.launchCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.nullValue

@RunWith(AndroidJUnit4::class)
class HMCListLocalDataSourceTest {

    private lateinit var database: HMCListDatabase
    private lateinit var datasource: HMCListLocalDataSource

    @Before
    fun init() {
        val context = InstrumentationRegistry.getContext()
        database = Room.inMemoryDatabaseBuilder(context,
                HMCListDatabase::class.java).build()
        datasource = HMCListLocalDataSource.getInstance(database.hmcDao())
    }

    @After
    fun cleanUp() {
        database.close()
        HMCListLocalDataSource.clearInstance()
    }

    /**
     * Tests saving a HMC List into the database and retrieving it
     */
    @Test
    fun insertAndGetHMCList() {
        launchCoroutine {
            datasource.saveHMCList(DEFAULT_HMCLIST)
            val loaded = datasource.getHMCList(DEFAULT_ID_DS)
            assertHMCList(loaded, HMCListLocalDataSourceTest.DEFAULT_ID_DS,
                    HMCListLocalDataSourceTest.DEFAULT_NAME_DS,
                    HMCListLocalDataSourceTest.DEFAULT_DESCRIPTION_DS)
        }
    }

    /**
     * Tests saving a HMC lists and getting a list of HMC lists
     */
    @Test
    fun insertAndGetManyHMCList() {
        launchCoroutine {
            datasource.saveHMCList(DEFAULT_HMCLIST)
            val loadedlist = datasource.getHMCLists()
            val loaded = loadedlist[0]
            assertHMCList(loaded, HMCListLocalDataSourceTest.DEFAULT_ID_DS,
                    HMCListLocalDataSourceTest.DEFAULT_NAME_DS,
                    HMCListLocalDataSourceTest.DEFAULT_DESCRIPTION_DS)
        }
    }

    /**
     * Tests saving a HMC List and deleting a HMC list
     */
    @Test
    fun insertAndDeleteHMCList() {
        launchCoroutine {
            datasource.saveHMCList(DEFAULT_HMCLIST)
            datasource.deleteHMCList(DEFAULT_ID_DS)
            val loaded = datasource.getHMCList(DEFAULT_ID_DS)
            assertThat(loaded, `is`(nullValue()))
        }
    }


    private fun assertHMCList(
            hmclist: HMCLists?,
            id: String,
            name: String,
            description: String
    ) {
        MatcherAssert.assertThat<HMCLists>(hmclist as HMCLists, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(hmclist.id, CoreMatchers.`is`(id))
        MatcherAssert.assertThat(hmclist.name, CoreMatchers.`is`(name))
        MatcherAssert.assertThat(hmclist.description, CoreMatchers.`is`(description))
    }

    companion object {
        private val DEFAULT_NAME_DS = "nameds"
        private val DEFAULT_DESCRIPTION_DS = "descriptionds"
        private val DEFAULT_ID_DS = "idds"

        private val DEFAULT_HMCLIST = HMCLists(DEFAULT_ID_DS, DEFAULT_NAME_DS, DEFAULT_DESCRIPTION_DS)
    }
}