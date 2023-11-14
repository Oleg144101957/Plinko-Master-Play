package app.plinko.masterplay.ui.theme.displays

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.plinko.masterplay.Const
import app.plinko.masterplay.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPresent(navHostController: NavHostController){

    val context = LocalContext.current
    val sp = context.getSharedPreferences(Const.SP, Context.MODE_PRIVATE)


    val text = remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.gamebackground),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        TextField(
            value = text.value,
            modifier = Modifier
                .align(Alignment.Center),
            onValueChange = {
                text.value = it
                sp.edit().putString(Const.USER_NAME, it).apply()
            }
        )

        Text(
            text = "Enter your name",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-64).dp)
        )
    }


}