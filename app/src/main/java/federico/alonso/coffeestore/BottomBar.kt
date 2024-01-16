package federico.alonso.coffeestore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import federico.alonso.coffeestore.ui.theme.Alternative2

data class NavPage(
    var route: String,
    var title: String,
    var icon: ImageVector
)
object Pages {
    val menuPage = NavPage("menu","Menu", Icons.Default.Menu)
    val offersPage = NavPage("offers", "Offers", Icons.Default.Star)
    val orderPage = NavPage("order", "My Order", Icons.Default.ShoppingCart)
    val infoPage = NavPage("info","Info", Icons.Default.Info)
    val all = listOf(
        menuPage,
        offersPage,
        orderPage,
        infoPage
    )
}


@Composable
fun NavBar(
    selectedRoute: String = Pages.all[0].route,
    onRouteChange: (String)-> Unit){ // State hoisting, cuando cambia, ejecuta esta función.
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp)


    ) {
       for (page in Pages.all){
           NavBarItem(
               page,
               page.route == selectedRoute,
               modifier = Modifier
                   .clickable { // acá el menú se hace clickeable, y cuando se clickea, ejecuta la función
                        onRouteChange(page.route) // y se le pasa
                   })
       }
    }
}

@Composable
fun NavBarItem(
    page: NavPage,
    selected: Boolean = false,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
    ){
        val color = if (selected)
            Alternative2
            else MaterialTheme.colorScheme.onPrimary
        Image(
            page.icon,
            contentDescription = page.title,
            colorFilter = ColorFilter.tint(color), // para pintar los íconos
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp))
        Text(
            page.title,
            fontSize = 12.sp,
            color = color)
    }
}