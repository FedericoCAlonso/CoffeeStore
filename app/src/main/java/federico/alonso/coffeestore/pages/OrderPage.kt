package federico.alonso.coffeestore.pages


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import federico.alonso.coffeestore.data.DataManager
import federico.alonso.coffeestore.data.ItemInCart


@Composable
fun OrderPage(dataManager: DataManager) {

    if(dataManager.cart.isNotEmpty()){
        Column{
            CartItems(dataManager)
            DeliveryForm(dataManager)
        }

    }

}

@Composable
private fun StyleCard(content: @Composable () -> Unit ) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp)
    ){
            content()
    }
}

@Composable
fun DeliveryForm(dataManager: DataManager) {
    val user = remember { mutableStateOf("")}


    StyleCard{
        Text(
            "NAME",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )
        OutlinedTextField(
            modifier= Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                ),
            value = user.value,
            onValueChange = {
                            user.value = it
            },
            label = {
                Text("Name for order")
            },
            singleLine = true,
            shape = RoundedCornerShape(32.dp )
        )

    }

}

@Composable
private fun CartItems(dataManager: DataManager) {

    StyleCard {
        LazyColumn()
        {
            item {
                Text(
                    "ITEMS",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                )
            }
            items(dataManager.cart) { itemCart ->

                ItemDetails(itemCart, dataManager)

            }
        }
    }
}

@Composable
private fun ItemDetails(
    itemCart: ItemInCart,
    dataManager: DataManager
) {
    Row(
        //horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .weight(1F)
        ){
            Text(
                "${itemCart.quantity}x",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary)
        }// quantity

        Column(
            modifier = Modifier
                .weight(8F)
        ) {
            Text(itemCart.product.name)
        } // Product

        Column(
            modifier = Modifier
                .weight(2F)
        ) {
            Text("$${itemCart.product.price * itemCart.quantity}")
        } // total by product

        Column(
            modifier = Modifier
                .weight(1F)
        ){
            IconButton(
                onClick = {
                    dataManager.cartRemove(itemCart.product)
                }){
                Icon(
                    Icons.TwoTone.Delete,
                    contentDescription = "Decressing quantity",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        } // Delete icon button
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 8.dp),
        color = MaterialTheme.colorScheme.secondary
    )
}


