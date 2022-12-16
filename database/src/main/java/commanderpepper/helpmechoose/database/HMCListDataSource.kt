package commanderpepper.helpmechoose.database

import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues

interface HMCListDataSource {
    suspend fun getHMCLists(): List<HMCLists>

    suspend fun saveHMCList(hmcList: HMCLists)

    suspend fun deleteHMCList(id: String)

    suspend fun getHMCList(id: String): HMCLists

    suspend fun insertValue(hmclistsvalues: HMCListsValues)

    suspend fun getHMCListsValues(id : String): List<HMCListsValues>

    suspend fun getListOfValues(id: String): List<String>
}