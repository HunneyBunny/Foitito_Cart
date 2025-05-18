package com.example.foitito_cart

import android.app.Dialog
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foitito_cart.ui.theme.Foitito_cartTheme
import androidx.compose.ui.graphics.Color

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

    val items = listOf<Triple<String,Int, Double>>(
        Triple("Ψωμί Τοστ", 1, 1.40),
        Triple("Κασερί", 2, 2.20),
        Triple("Γάλα", 1, 1.20)
    )

    val total = items.sumOf { it.second * it.third }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.mainbg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable._cart),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(top = 60.dp).size(370.dp),
                alignment = Alignment.TopCenter
            )

            Row(modifier = Modifier.fillMaxWidth().offset(y = -310.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "Αντικείμενο",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
                Text(
                    text = "Ποσότητα",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
                Text(
                    text = "€",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            }

            items.forEach { (name, quantity, price) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(text = name, color = Color.Black, fontSize = 20.sp)
                    Text(text = quantity.toString(), color = Color.Black, fontSize = 20.sp)
                    Text(text = price.toString(), color = Color.Black, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = total.toString(),
                fontSize = 40.sp
            )
        }

        Row(modifier.fillMaxWidth().padding(bottom = 30.dp).align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically)
        {
            IconButton (onClick = {
                TODO()
            },
            ) {
                Image(
                    painter = painterResource(R.drawable.budget),
                    contentDescription = null,
                    modifier.size(200.dp),
                    alignment = Alignment.CenterStart,
                    alpha = 0.0f,
                )
            }

            IconButton (onClick = {
                addItem()
            },
            ) {
                Image(
                    painter = painterResource(R.drawable.circle_plus),
                    contentDescription = null,
                    modifier.size(200.dp),
                    alignment = Alignment.Center
                )
            }

            IconButton (onClick = {
                navController.navigate(Budget.root)
            },
            ) {
                Image(
                    painter = painterResource(R.drawable.final_button),
                    contentDescription = null,
                    modifier.size(200.dp),
                    alignment = Alignment.CenterEnd
                )
            }
        }
    }
}

fun addItem() {
    TODO("Not yet implemented")
}
