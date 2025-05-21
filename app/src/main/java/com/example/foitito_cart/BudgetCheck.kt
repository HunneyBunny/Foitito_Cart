package com.example.foitito_cart

import android.content.Context
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foitito_cart.ui.theme.Foitito_cartTheme
import kotlin.math.abs

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
    val context = LocalContext.current
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
                modifier = Modifier.align(Alignment.Center).padding(12.dp)
            )
        } else {
            Text(
                text = "Είσουν εκτός Μπάτζετ κατα "+ abs(limit).toString() + "€ :(",
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center).padding(12.dp)
            )
        }
        IconButton(
            onClick = {restartApp(context)},
            modifier = Modifier.scale(1.2f).align(Alignment.BottomCenter).padding(bottom = 80.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.restart),
                contentDescription = "Go to Budget",
                alignment = Alignment.Center
            )
        }
    }
}

fun restartApp(context: Context) {
    val intent = context.packageManager
        .getLaunchIntentForPackage(context.packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    Runtime.getRuntime().exit(0) // Optional: forcefully kill the old process
}