package commanderpepper.helpmechoose.ui.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import commanderpepper.helpmechoose.ui.R
import commanderpepper.helpmechoose.uimodel.HMCItem


@Composable
fun HMCListUI(modifier: Modifier = Modifier, hmcList: List<HMCItem>, onHMCClick: () -> Unit, onDeleteClick: () -> Unit){
    LazyRow(){
        hmcList.forEach { hmcItem ->
            item {
                HMCListRowUI(hmcItem = hmcItem, onHMCClick = { /*TODO*/ }) {
                    
                }
            }
        }
    }
}

@Composable
fun HMCListRowUI(modifier: Modifier = Modifier, hmcItem: HMCItem, onHMCClick: () -> Unit, onDeleteClick: () -> Unit){
    Row(
        modifier = modifier.clickable { onHMCClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicText(text = hmcItem.name)
        Image(modifier = Modifier.clickable { onDeleteClick() }, painter = painterResource(id = R.drawable.ic_delete), contentDescription = "Delete HMC List")
    }
}

@Composable
@Preview
fun HMCListRowUIPreview() {
    HMCListRowUI(hmcItem = HMCItem(id = "1", name = "This is a test"), onHMCClick = { }, onDeleteClick = { })
}