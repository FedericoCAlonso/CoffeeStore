package federico.alonso.coffeestore.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Esta clase se encarga de la información que maneja toda la aplicación, ya que no corresponde
// que se encargue los composables de esto
class DataManager : ViewModel() {
    var menu: List<Category> by mutableStateOf(listOf())

    init {
        fetchData()
    }
    fun fetchData(){

        // Ejecuta el getMenu en una corutina. Es decir, no se queda esperando.
        viewModelScope.launch {
                var menu = API.menuService.getMenu()

        }
    }
}