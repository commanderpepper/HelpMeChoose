package commanderpepper.helpmechoose.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import commanderpepper.helpmechoose.uimodel.HMCItem

/**
 * Data Class used to retrieve the name, id and list of a HMC List
 *
 * This is the data class for a row from HMC List Names
 * When inserting a HMC List into SQLite, this will be inserted first
 */

@Entity(tableName = "HMCListNames")
data class HMCLists(
    @PrimaryKey @ColumnInfo(name = "hmclistid") val id: String = "",
    @ColumnInfo(name = "name") val name: String = "") {

    /**
     * Probably will not be used
     */
//    @Ignore
//    var matrix: MutableMap<Pair<String, String>, String?> = mutableMapOf<Pair<String, String>, String?>()

    /**
     * Probably will not be used
     */
//    @Ignore
//    var uniquePairs: MutableMap<List<String>, String?> = mutableMapOf()


    /**
     * Will not be used
     */
//    fun defineMatrix(list: MutableSet<String>): MutableMap<Pair<String, String>, String?> {
//        return mutableMapOf<Pair<String, String>, String?>().apply {
//            list.forEach { outer -> list.forEach { inner -> this[(outer to inner)] = if (outer == inner) "=" else null } }
//        }
//    }

    /**
     * Will not be used
     */
//    fun defineSet(set: MutableSet<String>): MutableMap<List<String>, String?> {
//        return mutableMapOf<List<String>, String?>().apply {
//            set.forEach { outer ->
//                set.forEach { inner ->
//                    if (outer != inner) {
//                        this[listOf(outer, inner).sorted()] = ""
//                    }
//                }
//            }
//        }
//    }

    /**
     * Take a list of HMC List Values from the Database and create a matrix where data can be stored
     */
//    fun populateMatrix(list: List<HMCListsValues>): MutableList<Pair<Pair<String, String>, String>> {
//        return mutableListOf<Pair<Pair<String, String>, String>>().apply {
//            list.forEach { outer ->
//                list.forEach { inner -> this.add((outer.key1 to inner.key2) to inner.value) }
//            }
//        }
//    }

    /**
     * Used in the initial creation of a list
     * Each value will be set to ""
     * I guess I need to pass an ID for the id
     */
//    fun createHMCValues(list: List<String>, id: String): List<HMCListsValues> {
//        return mutableSetOf<HMCListsValues>().apply {
//            list.forEach { key1 ->
//                list.forEach { key2 ->
//                    if (key1 != key2 && !this.contains(HMCListsValues(id, key1, key2, "")) && !this.contains(HMCListsValues(id, key2, key1, ""))) {
//                        this.add(HMCListsValues(id, key1, key2, ""))
//                    }
//                }
//            }
//        }.toList()
//    }

    /**
     * Create a matrix from a list of HMC Lists Values
     */
//    fun makeMapFromHMCValues(list: List<HMCListsValues>): MutableMap<Pair<String, String>, String> {
//        return mutableMapOf<Pair<String, String>, String>().apply {
//            list.forEach {
//                this[it.key1 to it.key2] = it.value
//                this[it.key2 to it.key1] = when (it.value) {
//                    ">" -> "<"
//                    "<" -> ">"
//                    else -> "="
//                }
//            }
//        }
//    }

    /**
     * Makes a list of HMC Lists Values from a Map
     * Used to save stuff into the database
     * Should return a list, be 1/2 the size of the Map
     */
//    fun makeListOfValuesFromMap(map: MutableMap<Pair<String, String>, String>, id : String): List<HMCListsValues> {
//        val mapKeysInsert = mutableMapOf<Pair<String, String>, String>().apply {
//            map.forEach {
//                val sorted = listOf(it.key.first, it.key.second).sorted()
//                val sortedPair = (sorted[0] to sorted[1])
//                if (!this.containsKey(it.key) && !this.containsKey(sortedPair)) {
//                    this.put(it.key, it.value)
//                }
//            }
//        }
//
//        return mutableSetOf<HMCListsValues>().apply {
//            mapKeysInsert.forEach {
//                this.add(HMCListsValues(id, it.key.first, it.key.second, it.value))
//            }
//        }.toList()
//    }
}

fun HMCLists.toUIModel(): HMCItem {
    return HMCItem(
        id = this.id,
        name = this.name
    )
}

