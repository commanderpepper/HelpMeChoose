package commanderpepper.helpmechoose.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.room.HMCListDAO
import commanderpepper.helpmechoose.database.room.HMCListDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun testOne(){
        Assert.assertEquals(1, 1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun meow() = runTest {
        hmcListDAO.insertList(
            HMCLists("1", "This is a test")
        )
        val hmclistflow = hmcListDAO.getHMCLists()
        hmclistflow.test {
            val item = awaitItem()
            Assert.assertTrue(item.isNotEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }
}