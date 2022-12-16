package commanderpepper.helpmechoose.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import kotlinx.coroutines.flow.Flow

@Dao
interface HMCListDAO {

    /**
     * Select all HMC Lists from the HMC Lists Names table
     * Used on the
     *
     * @return all HMC lists
     */
    @Query("SELECT * FROM HMCListNames")
    fun getHMCLists(): Flow<List<HMCLists>>

    /**
     * Retrieve a single HMC Lists object using the id
     *
     * @return return a single HMC Lists object
     */
    @Query("SELECT * FROM HMCListNames WHERE hmclistid = :id")
    fun getHMCListById(id: String): Flow<HMCLists?>

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
    fun getHMCListsValues(id: String): Flow<List<HMCListsValues>>

    /**
     * Retrieve a list of items for key 1
     */
    @Query("SELECT key1 FROM HMCListsValues WHERE listid = :id")
    fun getListOfKey1(id: String): Flow<List<String>>

    /**
     * Retrieve a list of items for key 2
     */
    @Query("SELECT key2 FROM HMCListsValues WHERE listid = :id")
    fun getListOfKey2(id: String): Flow<List<String>>

    /**
     * Everything below this query will be used for the ViewModel + Flow stuff
     */

    /**
     * Retrieve a list of HMC Lists and wrap inside a flow
     */
    @Query("SELECT * FROM HMCListNames")
    fun getFlowHMCLists() : Flow<List<HMCLists>>
}