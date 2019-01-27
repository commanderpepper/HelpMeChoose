package commanderpepper.helpmechoose.data

import android.support.annotation.VisibleForTesting
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.data.model.HMCListsValues

class HMCListLocalDataSource private constructor(val hmclistdao: HMCListDAO) : HMCListDataSource {

    override suspend fun getHMCLists(): List<HMCLists> {
        return hmclistdao.getHMCLists()
    }

    override suspend fun saveHMCList(hmcList: HMCLists) {
        return hmclistdao.insertList(hmcList)
    }

    override suspend fun deleteHMCList(id: String) {
        hmclistdao.deleteHMCListById(id)
    }

    override suspend fun getHMCList(id: String): HMCLists {
        return hmclistdao.getHMCListById(id)!!
    }

    override suspend fun insertValue(hmclistsvalues: HMCListsValues) {
        return hmclistdao.insertValue(hmclistsvalues)
    }

    override suspend fun getHMCListsValues(id: String): List<HMCListsValues> {
        return hmclistdao.getHMCListsValues(id)
    }

    override suspend fun getListOfValues(id: String): List<String> {
        return hmclistdao.getListOfKey1(id) + hmclistdao.getListOfKey2(id)
    }

    companion object {
        private var INSTANCE: HMCListLocalDataSource? = null

        @JvmStatic
        fun getInstance(hmclistdao: HMCListDAO): HMCListLocalDataSource {
            if (INSTANCE == null) {
                synchronized(HMCListLocalDataSource::javaClass) {
                    INSTANCE = HMCListLocalDataSource(hmclistdao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }

    }

}