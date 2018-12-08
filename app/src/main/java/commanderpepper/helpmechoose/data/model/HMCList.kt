package commanderpepper.helpmechoose.data.model


data class HMCList(val list : MutableSet<String>,
                   val matrix: MutableMap<Pair<String, String>, String?>)