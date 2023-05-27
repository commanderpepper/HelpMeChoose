package commanderpepper.helpmechoose.database

import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import kotlinx.coroutines.flow.Flow

interface HMCListDataSource {
    fun getHMCLists(): Flow<List<HMCLists>>

    suspend fun saveHMCList(hmcList: HMCLists)

    suspend fun deleteHMCList(id: String)

    suspend fun getHMCList(id: String): Flow<HMCLists?>

    suspend fun insertValue(hmcListsValues: HMCListsValues)

    fun getHMCListsValues(id : String): Flow<List<HMCListsValues>>

    fun getListOfKeyOneValues(id: String): Flow<List<String>>

    fun getListOfKeyTwoValues(id: String): Flow<List<String>>
}