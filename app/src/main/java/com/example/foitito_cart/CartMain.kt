package com.example.foitito_cart

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foitito_cart.ui.theme.Foitito_cartTheme

class CartMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Foitito_cartTheme {
                TODO()
            }
        }
    }
}

@Composable
fun ItemList(modifier: Modifier,navController: NavHostController) {
    Box(modifier = modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.mainbg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "haiii",
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton (onClick = {
           navController.navigate(Budget.root)
        },
            modifier = Modifier.align(Alignment.BottomEnd)) {
            Image(
                painter = painterResource(R.drawable.final_button),
                contentDescription = null,
            )
        }

    }
}
