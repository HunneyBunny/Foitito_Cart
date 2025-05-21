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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foitito_cart.ui.theme.Foitito_cartTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.DisposableEffect

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
fun ItemListScreen(navController: NavHostController, modifier: Modifier = Modifier,budget: Double) {
    // This is where the items state lives — remembered once
    val items = remember { mutableListOf<Triple<String, Int, Double>>() }

    // Pass items and a function to add items down to ItemList UI
    ItemList(
        modifier = modifier,
        items = items,
        onAddItem = { newItem -> items.add(newItem) },
        navController = navController,
        budget
    )
}

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    items: List<Triple<String, Int, Double>>,
    onAddItem: (Triple<String, Int, Double>) -> Unit,
    navController: NavHostController,
    budget: Double
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    val total = items.sumOf { it.second * it.third }

    Box(modifier = modifier.fillMaxSize()) {
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
                modifier = Modifier.padding(top = 70.dp).scale(2.5f),
                alignment = Alignment.TopCenter
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
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
                    Text(
                        text = name,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = quantity.toString(),
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = price.toString() + "€",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Row(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(bottom = 140.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
            Text(
                text = "Μπάτζετ: "+String.format("%.2f", budget)+"€",
                fontSize = 24.sp,
            )
            Text(
                text = "Σύνολο: "+String.format("%.2f", total)+"€",
                fontSize = 24.sp,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { showDialog = true },
                modifier = Modifier.scale(1.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.circle_plus),
                    contentDescription = "Add item",
                    alignment = Alignment.Center
                )
            }

            IconButton(
                onClick = {
                    val budgetLimit = (budget - total).toString()
                    navController.navigate("${Budget.root}/$budgetLimit") },
                modifier = Modifier.scale(1.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.final_button),
                    contentDescription = "Go to Budget",
                    alignment = Alignment.CenterEnd
                )
            }
        }
    }

    if (showDialog) {
        AddItemDialog(
            onDismiss = { showDialog = false },
            onAddItem = {
                onAddItem(it)
                showDialog = false
                Toast.makeText(context, "Item added!", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun AddItemDialog(
    onDismiss: () -> Unit,
    onAddItem: (Triple<String, Int, Double>) -> Unit
) {
    val context = LocalContext.current
    // Use Android Dialog here as your layout is an XML dialog
    val dialog = remember { Dialog(context) }

    DisposableEffect(Unit) {
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.add_item_box)
            window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }

        val btnConf: Button = dialog.findViewById(R.id.confirm_button)
        val itemName: EditText = dialog.findViewById(R.id.itemName)
        val itemAmount: EditText = dialog.findViewById(R.id.itemAmount)
        val itemPrice: EditText = dialog.findViewById(R.id.itemPrice)

        val onClickListener = {
            val name = itemName.text.toString()
            val amount = itemAmount.text.toString().toIntOrNull()
            val price = itemPrice.text.toString().toDoubleOrNull()

            if (name.isNotBlank() && amount != null && price != null) {
                onAddItem(Triple(name, amount, price))
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }

        btnConf.setOnClickListener { onClickListener() }

        dialog.setOnDismissListener { onDismiss() }
        dialog.show()

        onDispose {
            dialog.dismiss()
        }
    }
}