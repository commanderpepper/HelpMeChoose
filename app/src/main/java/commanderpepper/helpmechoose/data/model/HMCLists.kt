package commanderpepper.helpmechoose.data.model

import android.arch.persistence.room.Entity

/**
 * Data Class used to retrieve the name, id and description of a HMC List
 *
 * This is the data class for a row from HMC List Names
 * When inserting a HMC List into SQLite, this will be inserted first
 */

@Entity(tableName = "HMCListNames")
data class HMCLists(var id: String = "",
                    var name: String = "",
                    var description: String = "")