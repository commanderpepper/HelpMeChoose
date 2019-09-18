package commanderpepper.helpmechoose.data.Room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import commanderpepper.helpmechoose.data.model.HMCLists
import commanderpepper.helpmechoose.data.model.HMCListsValues

@Database(entities = [HMCLists::class, HMCListsValues::class], version = 1)
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
                            "database.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}