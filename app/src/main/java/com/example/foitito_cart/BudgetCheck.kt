package com.example.foitito_cart

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foitito_cart.ui.theme.Foitito_cartTheme

class BudgetCheck : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Foitito_cartTheme {
                //FinalScreen(Modifier)
            }
        }
    }
}

@Composable
fun FinalScreen(modifier: Modifier,limit: Double) {
    Box(modifier = modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(R.drawable._cart),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).offset(0.dp, (-200).dp).size(280.dp)
        )
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).offset(0.dp, (-80).dp).size(80.dp)
        )
        if(limit >= 0){
            Text(
                text = "Είσουν εντός Μπάτζετ κατα "+ String.format("%.2f", limit) + "€!! Συγχαρητήρια",
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center).padding(start = 12.dp, end = 12.dp)
            )
        } else {
            Text(
                text = "Είσουν εκτός Μπάτζετ κατα "+ limit.toString() + "€ :(",
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center).padding(start = 12.dp, end = 12.dp)
            )
        }
    }
}