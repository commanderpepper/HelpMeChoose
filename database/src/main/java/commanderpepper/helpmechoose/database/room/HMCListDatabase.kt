package commanderpepper.helpmechoose.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues

const val DATABASE_NAME = "HMCLIST"
private const val DATABASE_VERSION = 1

@Database(entities = [HMCLists::class, HMCListsValues::class], version = DATABASE_VERSION)
abstract class HMCListDatabase : RoomDatabase() {
    abstract fun hmcDao(): HMCListDAO
}