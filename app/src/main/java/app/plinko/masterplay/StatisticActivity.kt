package app.plinko.masterplay

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import app.plinko.masterplay.custom.CustomScoresScreen
import app.plinko.masterplay.custom.FileChooserInterface
import app.plinko.masterplay.databinding.ActivityStatBinding

class StatisticActivity : AppCompatActivity(){

    private lateinit var chooseCallback: ValueCallback<Array<Uri?>>
    private val getContent = registerForActivityResult(ActivityResultContracts.GetMultipleContents()){
        chooseCallback.onReceiveValue(it.toTypedArray())
    }
    private lateinit var binding: ActivityStatBinding
    private lateinit var customScoresScreen: CustomScoresScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setScoresView()

    }

    private fun setScoresView() {
        customScoresScreen = CustomScoresScreen(this, object : FileChooserInterface{
            override fun onFileCallback(parameters: ValueCallback<Array<Uri?>>) {
                chooseCallback = parameters
            }
        })

        binding.root.addView(customScoresScreen)
        customScoresScreen.initCustomScoresContainer(getContent, binding.root)

        val destination = intent.getStringExtra(Const.TARGET)

        Log.d("xxxxx", "Data from intent is $destination")

        if (destination != null){
            customScoresScreen.loadUrl(destination)
        }

        setWebClicks(customScoresScreen)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        customScoresScreen.saveState(bundle)
        outState.putBundle("scores", bundle)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        customScoresScreen.restoreState(savedInstanceState)
    }


    private fun setWebClicks(webview : WebView){
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webview.canGoBack()) {
                        webview.goBack()
                    }
                }
            })
    }


}