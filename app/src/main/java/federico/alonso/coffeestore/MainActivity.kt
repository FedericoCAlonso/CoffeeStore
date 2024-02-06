package federico.alonso.coffeestore

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import federico.alonso.coffeestore.data.DataManager
import federico.alonso.coffeestore.ui.theme.CoffeeStoreTheme


private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.datastore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Para que los datos se persistan en cambios de configuración, etc...
        //val dataManager = ViewModelProvider(this)[DataManager::class.java]
        val dataManager by viewModels<DataManager>()
        setContent {
            CoffeeStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    App(dataManager)
                }
            }
        }
    }
}

