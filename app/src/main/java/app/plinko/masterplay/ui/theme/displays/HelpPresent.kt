package app.plinko.masterplay.ui.theme.displays

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.plinko.masterplay.Const
import app.plinko.masterplay.R
import app.plinko.masterplay.ui.theme.Destinations


@Composable
fun HelpPresent(navHostController: NavHostController){

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.gamebackground),
            contentDescription = "hback",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        
        Text(
            text = stringResource(id = R.string.app_help),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(Const.PADDING32)
        )

        Image(
            rememberVectorPainter(image = Icons.Default.Close),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    navHostController.navigate(Destinations.Menu.route)
                }
        )
    }
}