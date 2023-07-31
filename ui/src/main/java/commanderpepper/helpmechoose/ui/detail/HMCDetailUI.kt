package commanderpepper.helpmechoose.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun HMCDetailUI(
    modifier: Modifier = Modifier,
    viewModel: HMCDetailViewModel = koinNavViewModel(),
    onSortClick: (String) -> Unit
) {
    val hmcDetailUIState = viewModel.hmcDetailUIState.collectAsState()
    HMCDetailUI(
        modifier = modifier,
        hmcDetailUIState = hmcDetailUIState.value,
        onSortClick = onSortClick
    )
}

@Composable
fun HMCDetailUI(
    modifier: Modifier = Modifier,
    hmcDetailUIState: HMCDetailUIState,
    onSortClick: (String) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (hmcDetailUIState) {
            HMCDetailUIState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    BasicText(text = "Something went wrong")
                }
            }

            HMCDetailUIState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HMCDetailUIState.Success -> {
                Column(modifier = Modifier.padding(16.dp)) {
                    BasicText(
                        style = TextStyle(fontSize = 30.sp),
                        text = hmcDetailUIState.hmcDetailList.name
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)) {
                        hmcDetailUIState.hmcDetailList.items.forEach { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (item.itemTrophy != null && item.trophyColor != null) {
                                        Image(
                                            modifier = Modifier.size(item.itemSize.dp),
                                            painter = painterResource(id = item.itemTrophy),
                                            colorFilter = ColorFilter.tint(color = colorResource(id = item.trophyColor)),
                                            contentDescription = "Rank"
                                        )
                                    }
                                    BasicText(
                                        style = TextStyle(fontSize = item.textSize.sp),
                                        text = "${item.itemName}\t\tpts: ${item.value}"
                                    )
                                }
                            }
                        }
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { onSortClick(hmcDetailUIState.hmcDetailList.id) }) {
                    BasicText(text = "Sort ${hmcDetailUIState.hmcDetailList.name}")
                }
            }
        }
    }

}