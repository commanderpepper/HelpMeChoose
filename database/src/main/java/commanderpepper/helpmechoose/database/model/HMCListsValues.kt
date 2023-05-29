package commanderpepper.helpmechoose.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.TypeConverter

/**
 * Model for one row for the HMC Lists Values, for example
 * "A" , "B", "=", "1" represents one row. A matrix is formed from a list of HMCListsValues
 *
 * @id = unique id of the pair , value combo. This is a foreign key from HMC Lists Names
 * @key1 = The first item that forms the pair
 * @key2 = The second item that forms the pair
 * @value = can be = , > , <
 *
 */

@Entity(tableName = "HMCListsValues",
    primaryKeys = arrayOf("listid", "key1", "key2"),
    foreignKeys = arrayOf(ForeignKey(entity = HMCLists::class,
        parentColumns = arrayOf("hmclistid"),
        childColumns = arrayOf("listid"),
        onDelete = ForeignKey.CASCADE)))
data class HMCListsValues(@ColumnInfo(name = "listid") val id: String = "",
                          @ColumnInfo(name = "key1") val key1: String = "",
                          @ColumnInfo(name = "key2") val key2: String = "",
                          @ColumnInfo(name = "value") val relation: Relation)

enum class Relation(val value: String) {
    GREATER("greater"),
    LESS("less"),
    EQUAL("equal");
}

class RelationConverter{
    @TypeConverter
    fun relationToString(relation: Relation): String {
        return relation.value
    }

    @TypeConverter
    fun stringToRelation(relationAsString: String): Relation {
        return when (relationAsString){
            Relation.GREATER.value -> Relation.GREATER
            Relation.LESS.value -> Relation.LESS
            Relation.EQUAL.value -> Relation.EQUAL
            else -> Relation.EQUAL
        }
    }
}