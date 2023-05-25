package commanderpepper.helpmechoose.ui.newlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import commanderpepper.helpmechoose.ui.R

@Composable
fun HMCNewListUI(modifier: Modifier = Modifier, onSaveClick: () -> Unit, data: List<String>) {
    Column(modifier = modifier) {
        BasicText(text = "Title")
        Column() {
            data.forEach { 
                BasicText(text = it)
            }
        }
    }
}

@Composable
fun HMCNewListItemUI(modifier: Modifier = Modifier, newItem: String, editItemOnClick: () -> Unit){
    Row(modifier = modifier) {
        Image(modifier = Modifier.clickable { editItemOnClick() }, painter = painterResource(id = R.drawable.ic_edit), contentDescription = "Edit HMC item")
        BasicText(text = newItem)
    }
}

@Composable
@Preview
fun HMCNewListItemUIPreview(){
    HMCNewListItemUI(newItem = "This is a test") {
        
    }
}