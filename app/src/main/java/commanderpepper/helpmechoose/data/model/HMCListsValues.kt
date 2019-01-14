package commanderpepper.helpmechoose.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

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
data class HMCListsValues(@ColumnInfo(name = "listid") var id: String = "",
                          @ColumnInfo(name = "key1") var key1: String = "",
                          @ColumnInfo(name = "key2") var key2: String = "",
                          @ColumnInfo(name = "value") var value: String = "")