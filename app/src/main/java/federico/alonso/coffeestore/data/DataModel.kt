package federico.alonso.coffeestore.data


data class Product(
    var id: Int,
    var name: String,
    var price: Double,
    var description: String,
    var image: String
){
    val imageUrl
        get() = "https://firtman.github.io/coffeemasters/api/images/" + this.image
}

class ItemInCart(var product: Product, var quantity: Int =0)
{
    constructor(other: ItemInCart) : this(other.product, other.quantity)
}

// Se pueden llamar distinto al campo del json real de donde se va a buscar al información, pero
// hay que hacer un mapeo. Por ejemplo, si en el jason la categoría es "product name" como la variable
// no puede tener espacio, se puede hacer
// @SerializedName("product name") var name: String
data class Category (
    var name: String,
    var products: List<Product>
)

data class Offer(
    var id: Int,
    var title: String,
    var description: String
)