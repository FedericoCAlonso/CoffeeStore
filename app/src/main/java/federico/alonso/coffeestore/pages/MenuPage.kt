package federico.alonso.coffeestore.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import federico.alonso.coffeestore.data.DataManager
import federico.alonso.coffeestore.data.Product
import federico.alonso.coffeestore.ui.theme.Alternative1

// Función de extensión utilitaria
fun Double.toDecimalString() = "%.2f".format(this)

@Composable
fun MenuPage(dataManager: DataManager) {
    Box {
        if ( dataManager.menu.isNotEmpty()){
            // El lazy column no hace falta que tenga for, y tiene scroll por defecto
            LazyColumn {
                items(dataManager.menu){ category ->
                    Text(category.name,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 10.dp))
                        category.products.forEach{ product ->
                            ProductItem(product){
                                dataManager.cartAdd(it)
                                println(dataManager.cart)
                            }
                        }
                }
            }

        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }

    }


}

// onAdd es un evento, que ejecuta una expresión lambda
@Composable
fun ProductItem(product: Product, onAdd: (Product) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ){
        // AsyncImage es una dependencia agregada de un proyecto opensource.
        // "io.coil-kt:coil-compose:2.5.0
        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Image for ${product.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ){
            Column {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("$${product.price.toDecimalString()} ea")
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Alternative1,
                    contentColor = Color.White
                ),
                onClick = {
                    onAdd(product)
                },
            ){
                Text("Add")
            }
        }
    }
}
