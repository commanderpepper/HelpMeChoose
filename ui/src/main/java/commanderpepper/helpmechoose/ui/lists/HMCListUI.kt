package commanderpepper.helpmechoose.ui.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun HMCListUI(modifier: Modifier = Modifier, hmcList: List<String>, onHMCClick: () -> Unit, onDeleteClick: () -> Unit){
    LazyRow(){
        hmcList.forEach {
            item {

            }
        }
    }
}

@Composable
fun HMCListRowUI(modifier: Modifier = Modifier, hmcItem: String, onHMCClick: () -> Unit, onDeleteClick: () -> Unit){
    Row(modifier = modifier.clickable { onHMCClick() }, horizontalArrangement = Arrangement.SpaceBetween) {
        BasicText(text = hmcItem)
        BasicText(modifier = Modifier.clickable { onDeleteClick() }, text = "Delete")
    }
}

@Composable
@Preview
fun HMCListRowUIPreview() {
    HMCListRowUI(hmcItem = "This is a test", onHMCClick = { }, onDeleteClick = { })
}