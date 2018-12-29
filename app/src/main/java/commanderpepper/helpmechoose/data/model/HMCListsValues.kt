package commanderpepper.helpmechoose.data.model

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

data class HMCListsValues(var id: String = "",
                          var key1: String = "",
                          val key2: String = "",
                          var value: String = "") {

}