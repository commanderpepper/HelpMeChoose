package commanderpepper.helpmechoose.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import commanderpepper.helpmechoose.ui.lists.HMCListUI
import commanderpepper.helpmechoose.uimodel.HMCItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HMCHomeUI(
    modifier: Modifier = Modifier,
    hmcHomeViewModel: HMCHomeViewModel = koinViewModel<HMCHomeViewModel>(),
    onHMCClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAddClick: () -> Unit,
){
    val hmcList = hmcHomeViewModel.hmcItemList.collectAsState()
    HMCHomeUI(
        modifier = modifier,
        hmcList = hmcList.value,
        onHMCClick = onHMCClick,
        onDeleteClick = onDeleteClick,
        onAddClick = onAddClick
    )
}

@Composable
fun HMCHomeUI(
    modifier: Modifier = Modifier,
    hmcList: List<HMCItem>,
    onHMCClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()){
        if (hmcList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BasicText(text = "Add a list!")
            }
        }
        else {
            HMCListUI(hmcList = hmcList, onHMCClick = onHMCClick, onDeleteClick = onDeleteClick)
        }
        Button(modifier = Modifier.align(alignment = Alignment.BottomEnd).padding(16.dp), onClick = onAddClick){
            BasicText(text = "New List")
        }
    }

}

@Preview
@Composable
fun HMCHomeUIPreview(){
    HMCHomeUI(hmcList = emptyList(), onHMCClick = {}, onDeleteClick = {}, onAddClick = {})
}