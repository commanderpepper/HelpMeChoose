package commanderpepper.helpmechoose.database

import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import kotlinx.coroutines.flow.Flow

interface HMCListDataSource {
    suspend fun getHMCLists(): Flow<List<HMCLists>>

    suspend fun saveHMCList(hmcList: HMCLists)

    suspend fun deleteHMCList(id: String)

    suspend fun getHMCList(id: String): HMCLists

    suspend fun insertValue(hmcListsValues: HMCListsValues)

    suspend fun getHMCListsValues(id : String): Flow<List<HMCListsValues>>

    suspend fun getListOfValues(id: String): Flow<List<String>>
}