package commanderpepper.helpmechoose.ui.sort

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import commanderpepper.helpmechoose.database.model.Relation
import org.koin.androidx.compose.koinViewModel

@Composable
fun HMCSortListUI(
    modifier: Modifier = Modifier,
    hmcSortViewModel: HMCSortViewModel = koinViewModel(),
    doneSorting: () -> Unit
) {
    val hmcSortUIState = hmcSortViewModel.hmcSortUIState.collectAsState()
    HMCSortListUI(
        modifier = modifier,
        hmcSortUIState = hmcSortUIState.value,
        doneSorting = doneSorting,
        onButtonClick = hmcSortViewModel::setRelationship
    )
}

@Composable
fun HMCSortListUI(
    modifier: Modifier = Modifier,
    hmcSortUIState: HMCSortUIState,
    doneSorting: () -> Unit,
    onButtonClick: (String, String, Relation) -> Unit,
) {
    when (hmcSortUIState) {
        HMCSortUIState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BasicText(text = "Something went wrong")
            }
        }

        HMCSortUIState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        HMCSortUIState.DoneSorting -> {
            doneSorting()
        }

        is HMCSortUIState.Success -> {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(modifier = Modifier.padding(16.dp), text = "Which do you like better?")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        onButtonClick(
                            hmcSortUIState.hmcSortUI.keyOne,
                            hmcSortUIState.hmcSortUI.keyTwo,
                            Relation.EQUAL
                        )
                    }) {
                        Text("Neither")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {
                        onButtonClick(
                            hmcSortUIState.hmcSortUI.keyOne,
                            hmcSortUIState.hmcSortUI.keyTwo,
                            Relation.GREATER
                        )
                    }) {
                        Text(hmcSortUIState.hmcSortUI.keyOne)
                    }
                    Button(onClick = {
                        onButtonClick(
                            hmcSortUIState.hmcSortUI.keyOne,
                            hmcSortUIState.hmcSortUI.keyTwo,
                            Relation.LESS
                        )
                    }) {
                        Text(hmcSortUIState.hmcSortUI.keyTwo)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun HMCSortListUIPreview() {
//    HMCSortListUI(modifier = Modifier.fillMaxSize())
}