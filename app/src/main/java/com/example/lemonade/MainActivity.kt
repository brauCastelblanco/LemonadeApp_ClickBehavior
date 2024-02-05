@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.R.color.button_background
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember {mutableStateOf(1)}
    var randomCount by remember { mutableStateOf(0)}
    Scaffold (
        topBar = {
            TopAppBar(
                modifier = Modifier.height(55.dp),
                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.yellow),
                    titleContentColor = colorResource(id = R.color.black),
                ),
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Lemonade",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
        ){innerPadding ->
            Surface (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)

                ){
                when(currentStep) {
                    1 -> {
                            LemonTextandImage(
                                textLabel = R.string.lemon_tree,
                                image = R.drawable.lemon_tree,
                                description = R.string.select_lemon,
                                onImageClick = {
                                    currentStep = 2
                                }
                            )
                         }
                    2 ->{
                            LemonTextandImage(
                                textLabel = R.string.lemon,
                                image = R.drawable.lemon_squeeze,
                                description = R.string.squeeze_it,
                                onImageClick = {
                                    randomCount = (2..4).random()
                                    if(randomCount==3){
                                        currentStep = 3
                                    }
                                }
                            )
                        }
                    3 ->{
                        LemonTextandImage(
                            textLabel = R.string.glass_of_lemonade,
                            image = R.drawable.lemon_drink,
                            description = R.string.drink_it,
                            onImageClick = {
                                currentStep = 4
                            }
                        )
                        }
                    4 ->{
                        LemonTextandImage(
                            textLabel = R.string.empty_glass,
                            image = R.drawable.lemon_restart,
                            description = R.string.start_again,
                            onImageClick = {
                                currentStep = 1
                                randomCount = 0
                            }
                        )
                        }
                }
            }
    }
}
@Composable
fun LemonTextandImage(
    textLabel: Int,
    image: Int,
    description: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box( modifier = modifier){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = onImageClick,
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_background))
                )
                 {
                Image(painter = painterResource(id = image), contentDescription = null)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = description),
                fontSize = 18.sp
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonApp()
    }
}