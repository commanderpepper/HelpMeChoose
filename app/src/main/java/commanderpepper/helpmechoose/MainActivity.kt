package commanderpepper.helpmechoose

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import commanderpepper.helpmechoose.ui.lists.HMCListUI

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "list"){
                composable("list"){
                    HMCListUI(
                        hmcList = emptyList(),
                        onHMCClick = {},
                        onDeleteClick = {}
                    )
                }
            }
        }
    }
}