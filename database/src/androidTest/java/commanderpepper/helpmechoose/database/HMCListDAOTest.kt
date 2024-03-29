package commanderpepper.helpmechoose.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import commanderpepper.helpmechoose.database.model.Relation
import commanderpepper.helpmechoose.database.room.HMCListDAO
import commanderpepper.helpmechoose.database.room.HMCListDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HMCListDAOTest {
    private lateinit var hmcListDAO: HMCListDAO
    private lateinit var hmcListDatabase: HMCListDatabase

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        hmcListDatabase = Room.inMemoryDatabaseBuilder(context, HMCListDatabase::class.java).build()
        hmcListDAO = hmcListDatabase.hmcDao()
    }

    @After
    @Throws(IOException::class)
    fun close(){
        hmcListDatabase.close()
    }

    @Test
    fun makeMultipleListsGetMultipleLists() = runTest {
        hmcListDAO.insertList(
            HMCLists("1", "List One")
        )
        hmcListDAO.insertList(
            HMCLists("2", "List Two")
        )
        hmcListDAO.insertList(
            HMCLists("3", "List Three")
        )
        val hmclistflow = hmcListDAO.getHMCLists()
        hmclistflow.test {
            val hmcList = awaitItem()
            Assert.assertTrue(hmcList.isNotEmpty())
            Assert.assertEquals(3, hmcList.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun makeOneListGetOneList() = runTest {
        val hmcLists = HMCLists("1", "List One")
        hmcListDAO.insertList(hmcLists)
        val hmcListFromDBFlow = hmcListDAO.getHMCListById("1")
        hmcListFromDBFlow.test {
            val hmcListsFromDb = awaitItem()
            Assert.assertNotNull(hmcListsFromDb)
            Assert.assertEquals("List One", hmcListsFromDb!!.name)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun makeOneListModifyListGetOneListModified() = runTest {
        val hmcLists = HMCLists("1", "List One")
        hmcListDAO.insertList(hmcLists)

        val hmcListFromDBBeforeModification = hmcListDAO.getHMCListById("1").first()
        Assert.assertNotNull(hmcListFromDBBeforeModification)
        hmcListDAO.insertList(hmcListFromDBBeforeModification!!.copy(name = "List One Modified"))

        val hmcListFromDBFlow = hmcListDAO.getHMCListById("1")
        hmcListFromDBFlow.test {
            val hmcListsFromDb = awaitItem()
            Assert.assertNotNull(hmcListsFromDb)
            Assert.assertEquals("List One Modified", hmcListsFromDb!!.name)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun makeOneListInsertValuesCheckForValues() = runTest {
        val hmcListId = "1"
        val hmcLists = HMCLists(hmcListId, "List One")
        hmcListDAO.insertList(hmcLists)

        val hmcListValueOne = HMCListsValues(id = hmcListId, key1 = "Key One", key2 = "Key Two", Relation.GREATER)
        val hmcListValueTwo = HMCListsValues(id = hmcListId, key1 = "Key One Alt", key2 = "Key Two Alt", Relation.LESS)
        hmcListDAO.insertValue(hmcListValueOne)
        hmcListDAO.insertValue(hmcListValueTwo)

        val keyOne = hmcListDAO.getHMCListsValues(hmcListId)
        keyOne.test {
            val hmcListValueFromDb = this.awaitItem()
            Assert.assertTrue(hmcListValueFromDb.isNotEmpty())
            Assert.assertEquals(2, hmcListValueFromDb.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun makeOneListInsertListDeleteListCheckForNoLists() = runTest {
        val hmcListId = "1"
        val hmcLists = HMCLists(hmcListId, "List One")
        hmcListDAO.insertList(hmcLists)

        hmcListDAO.deleteHMCListById(hmcListId)

        val hmcListsFlow = hmcListDAO.getHMCLists()
        hmcListsFlow.test {
            val list = awaitItem()
            Assert.assertTrue(list.isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }
}