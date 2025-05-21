package com.example.foitito_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foitito_cart.ui.theme.Foitito_cartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Foitito_cartTheme {
                MyNavigation()
            }
        }
    }
}

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home.root) {
        composable(Home.root) {
            HomeScreen(modifier = Modifier,navController)
        }
        composable("${Cart.root}/{budget}",
            arguments = listOf(navArgument("budget") { type = NavType.StringType })
        ) { backStackEntry ->
            val budget = backStackEntry.arguments?.getString("budget")?.toDoubleOrNull() ?: 0.0
            ItemListScreen(navController, modifier = Modifier, budget = budget )
        }
        composable("${Budget.root}/{budgetLimit}",
            arguments = listOf(navArgument("budgetLimit") { type = NavType.StringType })){backStackEntry ->
            val budgetLimit = backStackEntry.arguments?.getString("budgetLimit")?.toDoubleOrNull() ?: 0.0
            FinalScreen(Modifier, limit = budgetLimit)
        }
    }
}
