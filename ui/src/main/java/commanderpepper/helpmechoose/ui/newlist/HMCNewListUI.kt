package commanderpepper.helpmechoose.ui.newlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import commanderpepper.helpmechoose.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HMCNewListUI(modifier: Modifier = Modifier, data: List<String>, onSaveClick: () -> Unit) {
    val showNewItemAlertDialog = remember { mutableStateOf(false) }
    if(showNewItemAlertDialog.value){
        AlertDialog(onDismissRequest = { showNewItemAlertDialog.value = false }) {
            Column() {
                var newItem : String = ""
                TextField(value = newItem, onValueChange = { newValue -> newItem = newValue })
                Button(onClick = {
                    //TODO: Add new item to whatever list contains data being displayed
                    showNewItemAlertDialog.value = false
                }) {
                    BasicText(text = "Save Item")
                }
            }
        }
    }
    Column(modifier = modifier) {
        BasicText(text = "Title")
        Button(onClick = { showNewItemAlertDialog.value = true }) {
            BasicText("Add new item")
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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

@Preview(device = Devices.PIXEL_3A)
@Composable
fun HMCNewListUIPreview(){
    HMCNewListUI(modifier = Modifier.fillMaxSize(), data = listOf("One", "Two", "Three", "Four")){

    }
}

@Composable
@Preview
fun HMCNewListItemUIPreview(){
    HMCNewListItemUI(newItem = "This is a test") {
        
    }
}