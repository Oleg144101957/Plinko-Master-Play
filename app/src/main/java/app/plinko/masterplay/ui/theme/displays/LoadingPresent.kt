package app.plinko.masterplay.ui.theme.displays

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.plinko.masterplay.R
import app.plinko.masterplay.ui.theme.Destinations
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay


@Composable
fun LoadingPresent(navHostController: NavHostController, delay: Long) {


    val offsetY = remember {
        Animatable(150f)
    }

    LaunchedEffect(Unit){
        delay(delay)
        navHostController.navigate(Destinations.Menu.route)
    }

    LaunchedEffect(Unit){
        offsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 2400,
                delayMillis = 100,
                easing = LinearEasing
            )
        )
    }


    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.loadingbackground),
            contentDescription = "lb",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = R.drawable.bus),
            contentDescription = "bus",
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = offsetY.value.dp)
        )

        Text(
            text = "Loading...",
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.Black,
            fontSize = 32.sp
        )

    }

    BackHandler(enabled = true) {

    }
}