package commanderpepper.helpmechoose.ui.newlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import commanderpepper.helpmechoose.ui.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HMCNewListUI(
    modifier: Modifier = Modifier,
    navController: NavController,
    hmcNewListViewModel: HMCNewListViewModel = koinViewModel<HMCNewListViewModel>()
) {
    val newsListUI = hmcNewListViewModel.newListUIState.collectAsState()
    val saveToDbStates = hmcNewListViewModel.saveToDBStates.collectAsState()

    HMCNewListUI(
        modifier = modifier,
        data = newsListUI.value,
        saveToDBStates = saveToDbStates.value,
        onAddName = hmcNewListViewModel::addName,
        onAddItem = hmcNewListViewModel::addItem,
        onSaveClick = hmcNewListViewModel::saveToDB,
        onErrorDismiss = hmcNewListViewModel::dismissClicked,
        onItemRemove = hmcNewListViewModel::removeItem,
        onSuccessfulCreation = {
            navController.popBackStack()
        }
    )
}

@Composable
fun HMCNewListUI(
    modifier: Modifier = Modifier,
    data: NewListUIState,
    saveToDBStates: SaveToDBStates,
    onAddName: (String) -> Unit,
    onAddItem: (String) -> Unit,
    onSaveClick: () -> Unit,
    onErrorDismiss: () -> Unit,
    onItemRemove: (String) -> Unit,
    onSuccessfulCreation: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            HMCNewListNameForm(
                modifier = Modifier.fillMaxWidth(),
                name = data.name,
                onSaveInput = onAddName
            )
            HMCNewListItemForm(
                modifier = Modifier.fillMaxWidth(),
                name = data.name,
                onSaveInput = onAddItem
            )
            BasicText(text = if (data.name.isEmpty()) "Add a new name please" else "List Name: ${data.name}")
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                data.values.forEach { item ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.clickable { onItemRemove(item) },
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Remove item",
                            colorFilter = ColorFilter.tint(color = Color.Red)
                        )
                        BasicText(text = "•\t$item")
                    }
                }
            }
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.BottomEnd)
            .padding(16.dp),
            onClick = { onSaveClick() }) {
            BasicText(text = "Save your new list")
        }
        when (saveToDBStates) {
            is SaveToDBStates.Error -> {
                AlertDialog(
                    onDismissRequest = { onErrorDismiss() },
                    title = {
                        BasicText(text = "What went wrong?")
                    },
                    text = {
                        BasicText(text = saveToDBStates.message)
                    },
                    confirmButton = {
                        Button(onClick = { onErrorDismiss() }) {
                            BasicText(text = "Let's try that again")
                        }
                    })
            }

            SaveToDBStates.NoAction -> {
                // Do nothing
            }

            SaveToDBStates.Success -> {
                onSuccessfulCreation()
            }
        }
    }
}

@Composable
fun HMCNewListNameForm(
    modifier: Modifier = Modifier,
    name: String,
    onSaveInput: (String) -> Unit
) {
    val textFieldValue = remember { mutableStateOf(name) }

    TextField(
        modifier = modifier,
        value = textFieldValue.value,
        onValueChange = {
            textFieldValue.value = it
            onSaveInput(textFieldValue.value)
        }
    )
}

@Composable
fun HMCNewListItemForm(
    modifier: Modifier = Modifier,
    name: String,
    onSaveInput: (String) -> Unit
) {
    val textFieldValue = remember { mutableStateOf(name) }

    TextField(
        modifier = modifier,
        value = textFieldValue.value,
        onValueChange = {
            textFieldValue.value = it
        },
        trailingIcon = {
            Image(
                modifier = Modifier.clickable {
                    onSaveInput(textFieldValue.value)
                    textFieldValue.value = ""
                },
                colorFilter = ColorFilter.tint(color = Color.Blue),
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add Item"
            )
        }
    )
}

@Preview(device = Devices.PIXEL_3A)
@Composable
fun HMCNewListUIWithoutDataPreview() {
    HMCNewListUI(
        data = NewListUIState(),
        saveToDBStates = SaveToDBStates.NoAction,
        onAddName = { _ -> },
        onAddItem = { _ -> },
        onSaveClick = { },
        onErrorDismiss = { },
        onItemRemove = { _ -> },
        onSuccessfulCreation = { }
    )
}

@Preview(device = Devices.PIXEL_3A)
@Composable
fun HMCNewListUIWithDataPreview() {
    HMCNewListUI(
        data = NewListUIState(name = "Preview", listOf("Pizza", "Cheeseburgers", "Fries")),
        saveToDBStates = SaveToDBStates.NoAction,
        onAddName = { _ -> },
        onAddItem = { _ -> },
        onSaveClick = { },
        onErrorDismiss = { },
        onItemRemove = { _ -> },
        onSuccessfulCreation = { }
    )
}