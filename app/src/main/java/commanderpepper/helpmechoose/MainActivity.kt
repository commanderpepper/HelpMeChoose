package commanderpepper.helpmechoose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import commanderpepper.helpmechoose.ui.detail.HMCDetailUI
import commanderpepper.helpmechoose.ui.home.HMCHomeUI
import commanderpepper.helpmechoose.ui.newlist.HMCNewListUI
import commanderpepper.helpmechoose.ui.sort.HMCSortListUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    HMCHomeUI(
                        onHMCClick = { listId ->
                            navController.navigate("detail/$listId")
                        },
                        onAddClick = {
                            navController.navigate(route = "newlist")
                        }
                    )
                }
                composable("newlist") {
                    HMCNewListUI(navController = navController)
                }
                composable("detail/{listId}") {
                    HMCDetailUI(onSortClick = { listId ->
                        navController.navigate("sort/$listId")
                    })
                }
                composable("sort/{listId}") {
                    HMCSortListUI(doneSorting = {
                        navController.navigateUp()
                    })
                }
            }
        }
    }
}