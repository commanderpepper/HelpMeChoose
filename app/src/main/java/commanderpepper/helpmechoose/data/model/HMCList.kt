package commanderpepper.helpmechoose.data.model


data class HMCList(val list: MutableSet<String>) {
    var matrix: MutableMap<Pair<String, String>, String?> = defineMatrix(list)
}

fun defineMatrix(list: MutableSet<String>): MutableMap<Pair<String, String>, String?> {
    return mutableMapOf<Pair<String, String>, String?>().apply {
        list.forEach { outer -> list.forEach { inner -> this[(outer to inner)] = if (outer == inner) "=" else null } }
    }
}