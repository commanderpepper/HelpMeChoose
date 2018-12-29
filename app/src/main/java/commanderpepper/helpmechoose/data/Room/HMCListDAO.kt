package commanderpepper.helpmechoose.data.Room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import commanderpepper.helpmechoose.data.model.HMCLists

@Dao
interface HMCListDAO{

    /**
     * Select all HMC Lists from the HMC Lists Names table
     * Used on the
     *
     * @return all HMC lists
     */
    @Query("SELECT * FROM HMCListNames") fun getHMCLists(): List<HMCLists>

    /**
     * Insert a HMC List into HMC List Names
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertList(hmcList : HMCLists)

    /**
     * Delete a HMC List from HMC Lists
     *
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM HMCListNames WHERE id = :id") fun deleteHMCListById(id : String): Int
}