package commanderpepper.helpmechoose.data

import commanderpepper.helpmechoose.data.model.HMCLists

/**
 * Main entry point for accessing tasks data.
 *
 * For simplicity, only getTasks() and getTask() return Result object. Consider adding Result to other
 * methods to inform the user of network/database errors or successful operations.
 *
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 *
 */

interface HMCListDataSource {
    suspend fun getHMCLists(): List<HMCLists>

    suspend fun saveHMCList(hmcList: HMCLists)

    suspend fun deleteHMCList(id: String)

    suspend fun getHMCList(id: String): HMCLists
}