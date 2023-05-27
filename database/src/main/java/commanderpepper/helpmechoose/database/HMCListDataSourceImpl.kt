package commanderpepper.helpmechoose.database

import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import commanderpepper.helpmechoose.database.room.HMCListDAO
import kotlinx.coroutines.flow.Flow

class HMCListDataSourceImpl(private val hmcListDAO: HMCListDAO): HMCListDataSource  {
    override fun getHMCLists(): Flow<List<HMCLists>> {
        return hmcListDAO.getHMCLists()
    }

    override suspend fun saveHMCList(hmcList: HMCLists) {
        hmcListDAO.insertList(hmcList)
    }

    override suspend fun deleteHMCList(id: String) {
        hmcListDAO.deleteHMCListById(id)
    }

    override suspend fun getHMCList(id: String): Flow<HMCLists?> {
        return hmcListDAO.getHMCListById(id)
    }

    override suspend fun insertValue(hmcListsValues: HMCListsValues) {
        hmcListDAO.insertValue(hmcListsValues)
    }

    override fun getHMCListsValues(id: String): Flow<List<HMCListsValues>> {
        return hmcListDAO.getHMCListsValues(id)
    }

    override fun getListOfKeyOneValues(id: String): Flow<List<String>> {
        return hmcListDAO.getListOfKey1(id)
    }

    override fun getListOfKeyTwoValues(id: String): Flow<List<String>> {
        return hmcListDAO.getListOfKey2(id)
    }
}