package com.example.foitito_cart

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.view.Window
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foitito_cart.ui.theme.Foitito_cartTheme

class CartMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Foitito_cartTheme {
                // Call your main composable here and pass navController if needed
                // For example:
                // ItemListScreen(navController = rememberNavController())
                // TODO: Replace with actual navController
            }
        }
    }
}

@Composable
fun ItemListScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    // This is where the items state lives — remembered once
    val items = remember { mutableStateListOf<Triple<String, Int, Double>>() }

    // Pass items and a function to add items down to ItemList UI
    ItemList(
        modifier = modifier,
        items = items,
        onAddItem = { newItem -> items.add(newItem) },
        navController = navController
    )
}

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    items: List<Triple<String, Int, Double>>,
    onAddItem: (Triple<String, Int, Double>) -> Unit,
    navController: NavHostController
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

        Text(
            text = "Σύνολο: $total",
            fontSize = 40.sp,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 130.dp)
        )

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
                onClick = { navController.navigate(Budget.root) },
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
