package federico.alonso.coffeestore.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CoffeeStoreApiService{
    // En retrofit hay que hacer una interfaz por cada servicio que queremos. Si es un get
    // hay que usar el decorador @GET y luego la dirección ( dentro de la url base) a donde
    // obtenerlo.
    //No hay que codificar nada, solo hay que definir la interfaz. Se pone suspend porque
    // es asincrónico
    @GET("menu.json")
    suspend fun getMenu(): List<Category>
}
object API {
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://firtman.github.io/coffeemasters/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // by lazy se usa cuando la variable se define ejecutando el código encerrado entre corchetes.
    val menuService: CoffeeStoreApiService by lazy{
        retrofit.create(CoffeeStoreApiService::class.java)
    }
}


