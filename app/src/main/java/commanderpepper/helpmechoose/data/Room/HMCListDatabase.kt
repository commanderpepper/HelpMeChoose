package commanderpepper.helpmechoose.data.Room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import commanderpepper.helpmechoose.data.model.HMCLists

@Database(entities = [HMCLists::class], version = 1)
abstract class HMCListDatabase : RoomDatabase() {
    abstract fun hmcDao(): HMCListDAO

    companion object {
        private var INSTANCE: HMCListDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): HMCListDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            HMCListDatabase::class.java,
                            "HMCLists.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}