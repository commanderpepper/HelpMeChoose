package commanderpepper.helpmechoose.data

import commanderpepper.helpmechoose.data.Room.HMCListDatabase
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.util.asyncCoroutine
import kotlinx.coroutines.*

/**
 * Concrete implementation of the database
 * Hey, I already having something like this, it's called HMC List Local Data Source. Might as well use that.
 * In the to do app that I used as a base for this app, this class is used to have calls on the local data source and the remote data source be in one place.
 * As this app does not have a remote local data source, there is no need for this class, yet.
 */

class HMCListRepository(val database: HMCListDatabase) {

    /**
     * Retrieve all hmc lists from the SQLite database
     */
    suspend fun getLists(): List<HMCLists> {
        return database.hmcDao().getHMCLists()
    }

    /**
     * Delete a hmc list in the SQLite database
     *
     * @param id - uuid of the hmc list being deleted
     */
    suspend fun deleteList(id: String){
        database.hmcDao().deleteHMCListById(id)
    }



}