package commanderpepper.helpmechoose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import commanderpepper.helpmechoose.ui.home.HMCHomeUI
import commanderpepper.helpmechoose.ui.newlist.HMCNewListItemUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    HMCHomeUI(
                        onHMCClick = {

                        },
                        onDeleteClick = {

                        },
                        onAddClick = {
                            navController.navigate(route = "newlist")
                        }
                    )
                }
                composable("newlist") {
                    HMCNewListItemUI(newItem = "Test") {

                    }
                }
                composable("edit") {

                }
                composable("sort") {
                    //TODO: Add sort Composable
                }
            }
        }
    }
}