package federico.alonso.coffeestore.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import federico.alonso.coffeestore.R
import federico.alonso.coffeestore.data.Offer
import federico.alonso.coffeestore.data.currentOffers
import federico.alonso.coffeestore.ui.theme.CoffeeStoreTheme

@Preview(showBackground = true)
@Composable
fun OffersPage() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ){
        for ( offer in currentOffers){
            Offer(offer)
        }

    }
}

@Composable
fun Offer(offer: Offer){

    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
            // para darle borde redondeado
            .clip(RoundedCornerShape(4.dp))
        ) {
        // Se pone afuera para que queden encimados.
        Image(
            painter = painterResource(R.drawable.background_pattern),
            contentDescription = "Background pattern",
            contentScale = ContentScale.FillWidth,
            // Hace coincidir el tamaño del padre. El padre es la Caja
            modifier = Modifier.matchParentSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                // para que ocupe todos el ancho
                .fillMaxWidth()
        ) {

            Text(
                text = offer.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = offer.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp)

            )
        }
    }
}

// Solo para el preview

@Preview(showBackground = true, widthDp= 380)
@Composable
private fun OfferView_preview() {
    // Hay que agregarle el tema para que lo tome.
    CoffeeStoreTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Offer(
                Offer(
                    1,
                    "Combo Navidad",
                    "Comprando dos cafés, te llevas un cupcake gratis"
                )
            )
        }
    }

}

