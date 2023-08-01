package commanderpepper.helpmechoose.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import commanderpepper.helpmechoose.ui.lists.HMCListUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun HMCHomeUI(
    modifier: Modifier = Modifier,
    hmcHomeViewModel: HMCHomeViewModel = koinViewModel<HMCHomeViewModel>(),
    onHMCClick: (String) -> Unit,
    onSortClick: (String) -> Unit,
    onAddClick: () -> Unit,
){
    val homeUIState = hmcHomeViewModel.hmcItemList.collectAsState()
    HMCHomeUI(
        modifier = modifier.padding(16.dp),
        homeUIState = homeUIState.value,
        onHMCClick = onHMCClick,
        onSortClick = onSortClick,
        onDeleteClick = hmcHomeViewModel::deleteList,
        onAddClick = onAddClick
    )
}

@Composable
fun HMCHomeUI(
    modifier: Modifier = Modifier,
    homeUIState: HomeUIState,
    onHMCClick: (String) -> Unit,
    onSortClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    onAddClick: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()){
        when(homeUIState){
            HomeUIState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is HomeUIState.NoData -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    BasicText(text = "Add a list!")
                }
            }
            is HomeUIState.Success -> {
                HMCListUI(hmcList = homeUIState.hmcItems, onHMCClick = onHMCClick, onDeleteClick = onDeleteClick, onSortClick = onSortClick)
            }
        }
        Button(
            modifier = Modifier.align(alignment = Alignment.BottomEnd),
            onClick = onAddClick){
            BasicText(text = "New List")
        }
    }

}

@Preview
@Composable
fun HMCHomeUIPreview(){
    HMCHomeUI(homeUIState = HomeUIState.Success(emptyList()), onHMCClick = {}, onDeleteClick = {}, onAddClick = {}, onSortClick = {})
}