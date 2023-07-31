package commanderpepper.helpmechoose.ui.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import commanderpepper.helpmechoose.ui.R
import commanderpepper.helpmechoose.uimodel.HMCItem


@Composable
fun HMCListUI(
    modifier: Modifier = Modifier,
    hmcList: List<HMCItem>,
    onHMCClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
    ) {
        items(items = hmcList) { item ->
            HMCListRowUI(hmcItem = item, onHMCClick = onHMCClick, onDeleteClick = onDeleteClick)
        }
    }
}

@Composable
fun HMCListRowUI(
    modifier: Modifier = Modifier,
    hmcItem: HMCItem,
    onHMCClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    val alertDialogIsVisible = remember { mutableStateOf(false) }
    if (alertDialogIsVisible.value) {
        AlertDialog(
            onDismissRequest = { alertDialogIsVisible.value = false },
            confirmButton = {
                Button(onClick = {
                    onDeleteClick(hmcItem.id)
                    alertDialogIsVisible.value = false
                }) {
                    BasicText(text = "Delete")
                }
            },
            title = {
                BasicText(text = "Delete list")
            },
            text = {
                BasicText(text = "Do you really want to delete ${hmcItem.name}?")
            })
    }
    Card {
        Row(
            modifier = modifier
                .padding(8.dp)
                .clickable { onHMCClick(hmcItem.id) }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicText(text = hmcItem.name)
            Image(
                modifier = Modifier.clickable { alertDialogIsVisible.value = true },
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "Delete HMC List"
            )
        }
    }
}

@Composable
@Preview
fun HMCListRowUIPreview() {
    HMCListRowUI(
        hmcItem = HMCItem(id = "1", name = "This is a test"),
        onHMCClick = { },
        onDeleteClick = { })
}