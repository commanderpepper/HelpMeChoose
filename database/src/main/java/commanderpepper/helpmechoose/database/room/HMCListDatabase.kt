package commanderpepper.helpmechoose.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import commanderpepper.helpmechoose.database.model.HMCLists
import commanderpepper.helpmechoose.database.model.HMCListsValues
import commanderpepper.helpmechoose.database.model.RelationConverter

const val DATABASE_NAME = "HMCLIST"
private const val DATABASE_VERSION = 1

@Database(entities = [HMCLists::class, HMCListsValues::class], version = DATABASE_VERSION)
@TypeConverters(RelationConverter::class)
abstract class HMCListDatabase : RoomDatabase() {
    abstract fun hmcDao(): HMCListDAO
}