package commanderpepper.helpmechoose.data.Room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.data.model.HMCListsValues

@Dao
interface HMCListDAO {

    /**
     * Select all HMC Lists from the HMC Lists Names table
     * Used on the
     *
     * @return all HMC lists
     */
    @Query("SELECT * FROM HMCListNames")
    fun getHMCLists(): List<HMCLists>

    /**
     * Retrieve a single HMC Lists object using the id
     *
     * @return return a single HMC Lists object
     */
    @Query("SELECT * FROM HMCListNames WHERE hmclistid = :id")
    fun getHMCListById(id: String): HMCLists?

    /**
     * Insert a HMC List into HMC List Names
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(hmcList: HMCLists)

    /**
     * Delete a HMC List from HMC Lists
     *
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM HMCListNames WHERE hmclistid = :id")
    fun deleteHMCListById(id: String): Int

    /**
     * Insert a HMC List Value row into HMCListsValues
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertValue(hmclistsvalues: HMCListsValues)

    /**
     * Retrieve all HMC Lists Value pairs that match the id given
     */
    @Query("SELECT * FROM HMCListsValues WHERE listid = :id")
    fun getHMCListsValues(id: String): List<HMCListsValues>

    /**
     * Retrieve a list of items for key 1
     */
    @Query("SELECT key1 FROM HMCListsValues WHERE listid = :id")
    fun getListOfKey1(id: String): List<String>

    /**
     * Retrieve a list of items for key 2
     */
    @Query("SELECT key2 FROM HMCListsValues WHERE listid = :id")
    fun getListOfKey2(id: String): List<String>
}