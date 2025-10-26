package com.example.mytaskgestionner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytaskgestionner.ui.theme.MyTaskGestionnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTaskGestionnerTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Text(
                            text = "\uD83D\uDCCB Ma Liste de taches ",
                            modifier = Modifier
                                .padding(innerPadding),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        NavHost(
                            navController = navController,
                            startDestination = "list"
                        ) {
                            composable("list") {
                                TaskScreen(navController = navController, modifier = Modifier.padding(innerPadding))
                            }
                            composable ("addTask"){
                                TaskForm(navController = navController, modifier = Modifier.padding(innerPadding), task = null)
                            }
                            composable(
                                "detail/{id}",
                                arguments = listOf(navArgument("id") { type = NavType.IntType })
                            ){
                                    backStackEntry ->
                                val id = backStackEntry.arguments?.getInt("id") ?: 0
//                                if (id == 0) {
//                                    Toast.makeText(
//                                        LocalContext.current,
//                                        "Invalid ID",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                    return@composable
//                                }
                                DetailScreen(
                                    id = id,
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding),
                                    task = Tasks.findById(id)
                                )
                            }
                            composable(
                                "edit/{id}",
                                arguments = listOf(navArgument("id") { type = NavType.IntType })
                            ) { backStackEntry ->
                                val id = backStackEntry.arguments?.getInt("id") ?: 0
                                TaskForm(
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding),
                                    task = Tasks.findById(id)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTaskGestionnerTheme {
        Greeting("Android")
    }
}