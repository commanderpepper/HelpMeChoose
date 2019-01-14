package commanderpepper.helpmechoose.data.model


/**
 * This class is what I think a complete HMCList object would look like
 * It would have
 *      matrix - a mutable map that contains pairs as keys
 *      name - a name
 *      description - a description of the list
 *      id - unique ID of the HMCList
 */

data class HMCList(val list: MutableSet<String>) {
    var matrix: MutableMap<Pair<String, String>, String?> = defineMatrixA(list)
}

fun defineMatrixA(list: MutableSet<String>): MutableMap<Pair<String, String>, String?> {
    return mutableMapOf<Pair<String, String>, String?>().apply {
        list.forEach { outer -> list.forEach { inner -> this[(outer to inner)] = if (outer == inner) "=" else null } }
    }
}