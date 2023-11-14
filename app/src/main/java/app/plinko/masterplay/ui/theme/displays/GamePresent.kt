package app.plinko.masterplay.ui.theme.displays

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.plinko.masterplay.Const
import app.plinko.masterplay.R
import kotlinx.coroutines.delay


@Composable
fun GamePresent(navHostController: NavHostController){

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val score = remember {
        mutableStateOf(0)
    }

    val fallingSpeed = remember {
        mutableStateOf(3500)
    }

    val ani1 = remember {
        Animatable(initialValue = 0f)
    }

    val ani2 = remember {
        Animatable(initialValue = 0f)
    }

    val ani3 = remember {
        Animatable(initialValue = 0f)
    }

    val ani4 = remember {
        Animatable(initialValue = 0f)
    }

    val ani5 = remember {
        Animatable(initialValue = 0f)
    }

    val ani6 = remember {
        Animatable(initialValue = 0f)
    }

    val ani7 = remember {
        Animatable(initialValue = 0f)
    }

    val ani8 = remember {
        Animatable(initialValue = 0f)
    }

    val ani9 = remember {
        Animatable(initialValue = 0f)
    }


    val isVisible1 = remember {
        mutableStateOf(true)
    }

    val isVisible2 = remember {
        mutableStateOf(true)
    }

    val isVisible3 = remember {
        mutableStateOf(true)
    }

    val isVisible4 = remember {
        mutableStateOf(true)
    }

    val isVisible5 = remember {
        mutableStateOf(true)
    }

    val isVisible6 = remember {
        mutableStateOf(true)
    }

    val isVisible7 = remember {
        mutableStateOf(true)
    }

    val isVisible8 = remember {
        mutableStateOf(true)
    }

    val isVisible9 = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = "anim1"){
        ani1.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 10, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim2"){
        ani2.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 300, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim3"){
        ani3.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim4"){
        ani4.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 50, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim5"){
        ani5.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 150, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim6"){
        ani6.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 500, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim7"){
        ani7.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 200, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim8"){
        ani8.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 300, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(key1 = "anim9"){
        ani9.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 100, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        repeat(32){
            delay(4000)
            isVisible1.value = true
            isVisible2.value = true
            isVisible3.value = true
            isVisible4.value = true
            isVisible5.value = true
            isVisible6.value = true
            isVisible7.value = true
            isVisible8.value = true
            isVisible9.value = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.gamebackground),
            contentDescription = "gb",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )



        if (isVisible1.value){
            Image(
                painter = painterResource(id = R.drawable.element1),
                contentDescription = "art1",
                modifier = Modifier
                    .offset(y = ani1.value.dp, x = 33.dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 1
                        isVisible1.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible2.value){
            Image(
                painter = painterResource(id = R.drawable.element2),
                contentDescription = "art2",
                modifier = Modifier
                    .offset(y = ani2.value.dp, x = (-33).dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 2
                        isVisible2.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible3.value){
            Image(
                painter = painterResource(id = R.drawable.element3),
                contentDescription = "art3",
                modifier = Modifier
                    .offset(y = ani3.value.dp, x = 48.dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 3
                        isVisible3.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible4.value){
            Image(
                painter = painterResource(id = R.drawable.element4),
                contentDescription = "art4",
                modifier = Modifier
                    .offset(y = ani4.value.dp, x = (-48).dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 4
                        isVisible4.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible5.value){
            Image(
                painter = painterResource(id = R.drawable.element5),
                contentDescription = "art5",
                modifier = Modifier
                    .offset(y = ani5.value.dp, x = 10.dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 2
                        isVisible5.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible6.value){
            Image(
                painter = painterResource(id = R.drawable.element6),
                contentDescription = "art6",
                modifier = Modifier
                    .offset(y = ani6.value.dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 3
                        isVisible6.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible7.value){
            Image(
                painter = painterResource(id = R.drawable.element7),
                contentDescription = "art7",
                modifier = Modifier
                    .offset(y = ani7.value.dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 1
                        isVisible7.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible8.value){
            Image(
                painter = painterResource(id = R.drawable.element8),
                contentDescription = "art8",
                modifier = Modifier
                    .offset(y = ani8.value.dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 2
                        isVisible8.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        if (isVisible9.value){
            Image(
                painter = painterResource(id = R.drawable.element9),
                contentDescription = "art6",
                modifier = Modifier
                    .offset(y = ani9.value.dp)
                    .align(Alignment.TopCenter)
                    .size(72.dp)
                    .clickable {
                        score.value += 1
                        isVisible9.value = false
                        fallingSpeed.value -= 250
                    }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.base1),
            contentDescription = "light section",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

        Image(
            painter = painterResource(id = R.drawable.base2),
            contentDescription = "base 2",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = (-48).dp)
        )


        Box(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = Const.PADDING32)
        ){
            Image(
                painter = painterResource(id = R.drawable.scoresback),
                contentDescription = "scores back",
                modifier = Modifier
                    .align(Alignment.Center)
            )

            Text(
                text = "${score.value} scores",
                modifier = Modifier
                    .offset(y = (-6).dp)
                    .align(Alignment.Center),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}