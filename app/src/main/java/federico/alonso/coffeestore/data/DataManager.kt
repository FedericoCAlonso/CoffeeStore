package federico.alonso.coffeestore.data



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



class DataManager : ViewModel() {

    var menu: List<Category> by mutableStateOf(listOf())
    // Se necesita que apunte a una nueva lista, para que detecte el cambio.
    var cart: List<ItemInCart> by mutableStateOf(listOf())

    init {

        fetchData()

    }
    fun fetchData(){
        // Ejecuta el getMenu en una corutina. Es decir, no se queda esperando.
        viewModelScope.launch {
            menu = API.menuService.getMenu()
        }
    }


    fun cartAdd(product: Product){
        val newCart = mutableListOf<ItemInCart>()
        cart.forEach {
            newCart.add(ItemInCart(it)) // genero una copia de cada item, ya que sospecho
                                        // que la lista apunta una referencia al primer elemento.
        }
        val existingItem = newCart.find{ it.product == product}
        if ( existingItem != null){
            existingItem.quantity++
        }
        else{
            newCart += ItemInCart(product,1)
        }
        cart = newCart.toList()

    }


    fun cartRemove(product: Product){
        val newCart = mutableListOf<ItemInCart>()
        cart.forEach {
            newCart.add(ItemInCart(it))
        }
        val existingItem = newCart.find{
            it.product == product
        }
        if ( existingItem != null){
            if ( existingItem.quantity > 1){
                existingItem.quantity--
            }
            else{
                newCart.remove(existingItem)
            }

            cart = newCart.toList()
        }
    }
    fun cartTotalPrice(): String{
        val sum = cart.sumOf { it.product.price * it.quantity }
        return String.format("$%.2f",sum)
    }


}
