package app.plinko.masterplay.ui.theme.displays

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.plinko.masterplay.Const
import app.plinko.masterplay.R
import app.plinko.masterplay.ui.theme.Destinations


@Composable
fun MenuPresent(navHostController: NavHostController){

    val context = LocalContext.current
    val sp = context.getSharedPreferences(Const.SP, Context.MODE_PRIVATE)
    val name = sp.getString(Const.USER_NAME, "user") ?: "user"

    val offsetX = remember {
        Animatable(150f)
    }

    LaunchedEffect(Unit){
        offsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1500,
                delayMillis = 200,
                easing = FastOutSlowInEasing
            )
        )
    }

    
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.loadingbackground),
            contentDescription = "main back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "Hello $name",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
        )
        
        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(Const.PADDING32)
            .offset(x = offsetX.value.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.text),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(Destinations.GamePlay.route)
                        }
                )

                Text(
                    text = "Play Plinko",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.text),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(Destinations.Settings.route)
                        }
                )

                Text(
                    text = "Game Settings",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.text),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(Destinations.Help.route)
                        }
                )

                Text(
                    text = "Help",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }

    BackHandler(enabled = true) {

    }

}