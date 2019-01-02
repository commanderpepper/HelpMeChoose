package commanderpepper.helpmechoose.data

import android.support.annotation.VisibleForTesting
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.model.HMCList
import commanderpepper.helpmechoose.data.model.HMCLists

class HMCListLocalDataSource private constructor(val hmclistdao: HMCListDAO) : HMCListDataSource {

    override suspend fun getHMCLists(): List<HMCLists> {
        val hmclists = hmclistdao.getHMCLists()
        return hmclists
    }

    override suspend fun saveHMCList(hmcList: HMCLists) {
        return hmclistdao.insertList(hmcList)
    }

    override suspend fun deleteHMCList(id: String) {
        hmclistdao.deleteHMCListById(id)
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