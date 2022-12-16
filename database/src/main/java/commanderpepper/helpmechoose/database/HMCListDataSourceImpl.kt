package commanderpepper.helpmechoose.database

import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import commanderpepper.helpmechoose.database.room.HMCListDAO
import kotlinx.coroutines.flow.Flow

class HMCListDataSourceImpl(private val hmcListDAO: HMCListDAO): HMCListDataSource  {
    override suspend fun getHMCLists(): Flow<List<HMCLists>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveHMCList(hmcList: HMCLists) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHMCList(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getHMCList(id: String): HMCLists {
        TODO("Not yet implemented")
    }

    override suspend fun insertValue(hmcListsValues: HMCListsValues) {
        TODO("Not yet implemented")
    }

    override suspend fun getHMCListsValues(id: String): Flow<List<HMCListsValues>> {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfValues(id: String): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}