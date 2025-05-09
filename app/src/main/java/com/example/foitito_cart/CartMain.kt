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
fun showCustomDialogBox() {
    val context = LocalContext.current
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.budget_dialog_box)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    val btnConf : Button = dialog.findViewById(R.id.confirm_button)
    val budget : EditText = dialog.findViewById(R.id.budget)

    btnConf.setOnClickListener{
        dialog.dismiss()
    }

    dialog.show()
    ItemList(Modifier, budget)
}

@Composable
fun ItemList(modifier: Modifier,budget : EditText) {
    Box(modifier = modifier.fillMaxSize()){
        Text(
            text = budget.getText().toString(),
            fontSize = 50.sp
        )
        Image(
            painter = painterResource(R.drawable.mainbg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}