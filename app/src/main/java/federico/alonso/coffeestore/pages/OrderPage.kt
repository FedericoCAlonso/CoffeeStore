package federico.alonso.coffeestore.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import federico.alonso.coffeestore.data.DataManager
import federico.alonso.coffeestore.data.ItemInCart
import federico.alonso.coffeestore.data.Product



@Composable
fun OrderPage(dataManager: DataManager) {

    if(dataManager.cart.isNotEmpty()){
       Box{
            LazyColumn()
                {
                    items(dataManager.cart){itemCart->

                        ItemQuantityModifier(dataManager, itemCart)

                        ItemDetails(itemCart)
                    }
                    item(

                    ){
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),

                            ){
                            Text(
                                "Total: ${dataManager.cartTotalPrice()}",
                                style = MaterialTheme.typography.titleLarge)

                        }
                    }
                }




        }

    }

}

@Composable
private fun ItemDetails(itemCart: ItemInCart) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(4F)
        ) {
            Text(itemCart.product.name, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier
                .weight(1F)
        ) {

            Text("$${itemCart.product.price * itemCart.quantity}")
        }

    }
}

@Composable
private fun ItemQuantityModifier(
    dataManager: DataManager,
    itemCart: ItemInCart
) {
    Row(
        //horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { dataManager.cartRemove(itemCart.product) }) {
            Icon(Icons.Filled.Remove, contentDescription = "Decrement quantity")
        }
//
        Text(text = itemCart.quantity.toString())
        IconButton(onClick = { dataManager.cartAdd(itemCart.product) }) {
            Icon(Icons.Filled.Add, contentDescription = "Increment quantity")

        }
    }
}

@Preview(showBackground = true, widthDp= 380)
@Composable
fun PrevItemDetails(){
    ItemDetails(itemCart = ItemInCart(Product(1, "Frapuchino", 2.5, "", ""),2))
}