package com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.model.Drinks
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.model.Margarita
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.ui.theme.MargaritaJetpackComposeMvvmHiltFlow_23_08_24Theme
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.utils.ApiState
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.viewmodel.MargaritaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val margaritaViewModel: MargaritaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MargaritaJetpackComposeMvvmHiltFlow_23_08_24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MargaritaData(margaritaViewModel = margaritaViewModel)
                }
            }
        }
    }
}

@Composable
fun MargaritaData(margaritaViewModel: MargaritaViewModel) {
    val margarita by margaritaViewModel.margaritaFlow.collectAsState(initial = ApiState.Loading)

    when(margarita){
        is ApiState.Success ->{
            LazyColumn{
                items((margarita as ApiState.Success).data.drinks){margaritaData->
                    MargaritaDataForList(drinks = margaritaData)
                }
            }
        }
        is ApiState.Loading->{
            CircularProgressIndicator()
        }
        is ApiState.Failure ->{
            Text(text = "${(margarita as ApiState.Failure).msg}")
        }
        is ApiState.Empty ->{
            Text(text = "${(margarita as ApiState.Empty)}")

        }
    }

}


@Composable
fun MargaritaDataForList(drinks: Drinks) {
    Card (modifier =
    Modifier
        .padding(horizontal = 8.dp, vertical = 8.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(4.dp)
    ){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Row{
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(drinks.strDrinkThumb)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Error Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape))

                Text(text = drinks.idDrink,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
                Text(text = drinks.strDrink,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
            }
           
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MargaritaJetpackComposeMvvmHiltFlow_23_08_24Theme {
       
    }
}