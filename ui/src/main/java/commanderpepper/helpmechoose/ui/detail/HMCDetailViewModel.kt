package commanderpepper.helpmechoose.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commanderpepper.helpmechoose.database.HMCListDataSource
import commanderpepper.helpmechoose.database.model.HMCListsValues
import commanderpepper.helpmechoose.database.model.Relation
import commanderpepper.helpmechoose.ui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class HMCDetailViewModel(
    savedStateHandle: SavedStateHandle,
    hmcListDataSource: HMCListDataSource
) : ViewModel() {

    private val listId: String = savedStateHandle["listId"]!!
    private val hmcListsFlow = hmcListDataSource.getHMCList(listId)
    private val hmcListValues = hmcListDataSource.getHMCListsValues(listId)

    val hmcDetailUIState =
        hmcListsFlow.catch { HMCDetailUIState.Error }.combine(hmcListValues) { list, values ->
            if (list != null && values.isNotEmpty()) {
                HMCDetailUIState.Success(
                    HMCDetailListUI(
                        id = list.id,
                        name = list.name,
                        items = convertHMCListValues(values)
                    )
                )
            } else {
                HMCDetailUIState.Error
            }
        }.flowOn(Dispatchers.IO).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            HMCDetailUIState.Loading
        )

    private fun convertHMCListValues(values: List<HMCListsValues>): List<HMCDetailListItem> {
        val map = mutableMapOf<String, Int>()

        values.forEach { value ->
            var pointsKeyOne = map.getOrPut(value.key1) { 0 }
            var pointsKeyTwo = map.getOrPut(value.key2) { 0 }
            when (value.relation) {
                Relation.GREATER -> {
                    pointsKeyOne += 1
                    pointsKeyTwo -= 1
                }

                Relation.LESS -> {
                    pointsKeyOne -= 1
                    pointsKeyTwo += 1
                }

                Relation.EQUAL -> {
                    // No change in points
                }
            }
            map.put(value.key1, pointsKeyOne)
            map.put(value.key2, pointsKeyTwo)
        }

        val sortedList = map.map { (item, itemValue) ->
            item to itemValue
        }.sortedBy { it.second }.asReversed()

        val result = sortedList.map { (itemName, value) ->
            HMCDetailListItem(
                itemName = itemName,
                value = value
            )
        }.mapIndexed { index, hmcDetailListItem ->
            when (index) {
                0 -> hmcDetailListItem.copy(
                    itemSize = 24,
                    textSize = 24,
                    trophyColor = R.color.gold_trophy,
                    itemTrophy = R.drawable.ic_trophy
                )

                1 -> hmcDetailListItem.copy(
                    itemSize = 20,
                    textSize = 20,
                    trophyColor = R.color.silver_trophy,
                    itemTrophy = R.drawable.ic_trophy
                )

                2 -> hmcDetailListItem.copy(
                    itemSize = 16,
                    textSize = 16,
                    trophyColor = R.color.bronze_trophy,
                    itemTrophy = R.drawable.ic_trophy
                )

                else -> hmcDetailListItem
            }
        }

        return result
    }
}

sealed class HMCDetailUIState() {
    object Loading : HMCDetailUIState()
    object Error : HMCDetailUIState()
    data class Success(val hmcDetailList: HMCDetailListUI) : HMCDetailUIState()

}

data class HMCDetailListUI(val id: String, val name: String, val items: List<HMCDetailListItem>)

data class HMCDetailListItem(
    val itemName: String,
    val value: Int,
    val textSize: Int = 12,
    val itemTrophy: Int? = null,
    val itemSize: Int = 16,
    val trophyColor: Int? = null
)