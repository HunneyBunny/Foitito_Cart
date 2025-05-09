package com.example.foitito_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foitito_cart.ui.theme.Foitito_cartTheme

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Foitito_cartTheme {
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier,navController: NavHostController) {
    Box(modifier = modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Image(
            painter = painterResource(R.drawable._cart),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).offset(0.dp, (-200).dp).scale(1.6f)
        )
        IconButton (onClick = {
            navController.navigate(Cart.root)
        },
            modifier = Modifier.align(Alignment.BottomCenter).offset(0.dp, (-80).dp).scale(2f)) {
            Image(
            painter = painterResource(R.drawable.home_button),
            contentDescription = null,
            )
        }

    }
}
