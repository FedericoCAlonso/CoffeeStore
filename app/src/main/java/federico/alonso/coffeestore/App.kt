package federico.alonso.coffeestore


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import federico.alonso.coffeestore.data.DataManager
import federico.alonso.coffeestore.pages.InfoPage
import federico.alonso.coffeestore.pages.MenuPage
import federico.alonso.coffeestore.pages.OffersPage
import federico.alonso.coffeestore.pages.OrderPage
import federico.alonso.coffeestore.ui.theme.BottomBarUI


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(dataManager: DataManager) {
    var currentPage: MutableState<String> = remember { mutableStateOf("menu") }



    // Scaffold es como un template con barra de título
    // Main y footer
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text("Coffee Master")
                    }

                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(bottom = BottomBarUI.marginBottom)
            ){
                when(currentPage.value){
                    Pages.menuPage.route -> MenuPage(dataManager)
                    Pages.offersPage.route -> OffersPage()
                    Pages.orderPage.route -> OrderPage(dataManager)
                    Pages.infoPage.route -> InfoPage()
                }

            }

        },
        bottomBar = { // cuando se clickea un item del menu, se ejecuta onRouteChange y actualiza
            NavBar(currentPage.value , onRouteChange ={ // el estado del acitivity, ya que
                currentPage.value = it                          // currentPage es una variable de estado
            })
        }

    )

}