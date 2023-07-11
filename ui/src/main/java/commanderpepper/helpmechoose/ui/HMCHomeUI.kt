package commanderpepper.helpmechoose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import commanderpepper.helpmechoose.ui.lists.HMCListUI
import commanderpepper.helpmechoose.uimodel.HMCItem

@Composable
fun HMCHomeUI(
    modifier: Modifier = Modifier,
    hmcList: List<HMCItem>,
    onHMCClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    if (hmcList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicText(text = "Add a list!")
        }
    }
    else {
        HMCListUI(hmcList = hmcList, onHMCClick = onHMCClick, onDeleteClick = onDeleteClick)
    }
}

@Preview
@Composable
fun HMCHomeUIPreview(){
    HMCHomeUI(hmcList = emptyList(), onHMCClick = {}, onDeleteClick = {}, onAddClick = {})
}