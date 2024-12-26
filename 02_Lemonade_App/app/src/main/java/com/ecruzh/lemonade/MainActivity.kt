package com.ecruzh.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ecruzh.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier){
    var squeezes by remember { mutableIntStateOf((2..4).random())}
    var step by remember { mutableIntStateOf(1) }
    val imageResult = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val imageContDesc = when(step){
        1 -> R.string.lemon_tree_desc
        2 -> R.string.lemon_desc
        3 -> R.string.glass_desc
        else -> R.string.empty_glass_desc
    }    
    val description = when(step){
        1 -> R.string.step_1
        2 -> R.string.step_2
        3 -> R.string.step_3
        else -> R.string.step_4
    }
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier =  Modifier.clickable {
                when(step){
                    1,3 -> step++
                    2 -> {
                        if(squeezes == 1){
                            step++
                            squeezes = (2..4).random()
                        }else{
                            squeezes--
                        }
                    }
                    else -> step = 1
                }

            },
            painter = painterResource(id = imageResult),
            contentDescription = stringResource(id = imageContDesc))
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = stringResource(id = description))
    }
    
}

@Preview(showSystemUi = true)
@Composable
fun LemonadeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Lemonade(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }

}