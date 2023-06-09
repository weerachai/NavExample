package com.example.navexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navexample.ui.theme.NavExampleTheme

//sealed class Screen(val route: String) {
//    object Home: Screen("home")
//    object Greet: Screen("greet")
//}

enum class Screen {
    Home, Greet
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) {
                            HomeScreen(navController = navController)
                        }
                        composable(Screen.Greet.name) {
                            GreetingScreen("Android",
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(name: String, navController: NavController, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Button(onClick = { navController.navigate("greet") }) {
        Text("Click Me")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavExampleTheme {
        val navController = rememberNavController()
        HomeScreen(navController, modifier = Modifier)
    }
}