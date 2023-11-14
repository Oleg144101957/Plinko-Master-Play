package app.plinko.masterplay.custom

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.util.Log
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.result.ActivityResultLauncher
import app.plinko.masterplay.Const
import app.plinko.masterplay.MainActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class CustomScoresScreen(
    private val context: Context,
    private val onFileChoose: FileChooserInterface
) : WebView(context) {

    private val additionalScope = MainScope()
    private val sp = context.getSharedPreferences(Const.SP, Context.MODE_PRIVATE)
    private val savedLink = sp.getString(Const.CURRENT_DATA, Const.EMPTY) ?: Const.EMPTY
    private val gadid = sp.getString(Const.GAID, "no_gaid")
    private val appsuid = "3e556775-0af9-4264-be88-18ac6bf2f02a"
    private val pushtokenuuid = "MjllNDY1ZTctMjMxMS00NTNjLWI2ZGQtZDIxNzk5NDJjMWQ3"



    private val listOfParts = listOf("htt", "ps:", "//ft-", "apps.", "com/")
    private val listOfParts2 = listOf("htt", "ps:", "//fi", "rst")

    fun initCustomScoresContainer(content: ActivityResultLauncher<String>, root: FrameLayout){
        with(settings){
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            allowContentAccess = true
            useWideViewPort = true
            mediaPlaybackRequiresUserGesture = true
            pluginState = WebSettings.PluginState.ON
            cacheMode = WebSettings.LOAD_NORMAL
            loadsImagesAutomatically = true
            mixedContentMode = 0
            setEnableSmoothTransition(true)
            databaseEnabled = true
            savePassword = true
            allowUniversalAccessFromFileURLs = true
            saveFormData = true
            allowFileAccess = true
            javaScriptCanOpenWindowsAutomatically = true
            allowFileAccessFromFileURLs = true
            setSupportMultipleWindows(true)
            loadWithOverviewMode = true
            domStorageEnabled = true
            setJavaScriptEnabled(true)
            userAgentString = userAgentString.changerAgent()
            addJavascriptInterface(JsObject(), "Android")
        }

        setWebContentsDebuggingEnabled(true)
        isSaveEnabled = true
        isFocusableInTouchMode = true
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        webViewClient = getWVC()
        webChromeClient = getWCC(content, root)

    }


    private fun getWVC(): WebViewClient {
        return object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                additionalScope.launch {
                    CookieManager.getInstance().flush()
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                if (url == listOfParts[0]+listOfParts[1]+listOfParts[2]+listOfParts[3]+listOfParts[4]){
                    val intent = Intent(context, MainActivity::class.java)
                    sp.edit().putString(Const.CURRENT_DATA, Const.WARNING).apply()
                    context.startActivity(intent)
                }


                if (!savedLink.startsWith(listOfParts2[0]+listOfParts2[1]+listOfParts2[2]+listOfParts2[3]) && savedLink != Const.WARNING){
                    sp.edit().putString(Const.CURRENT_DATA, listOfParts2[0]+listOfParts2[1]+listOfParts2[2]+listOfParts2[3]+".ua").apply()
                }

                Log.d("xxxxx", "The url is $url")
            }
        }
    }

    private fun getWCC(content: ActivityResultLauncher<String>, rootElelement: FrameLayout): WebChromeClient{
        return object : WebChromeClient(){

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri?>>,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                onFileChoose.onFileCallback(filePathCallback)
                content.launch("image/*")

                return true
            }

            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message?
            ): Boolean {

                val newScoreView = PaymentScreen(context)
                newScoreView.initialPaymentScreen(rootElelement)
                rootElelement.addView(newScoreView)
                val trans = resultMsg?.obj as WebView.WebViewTransport
                trans.webView = newScoreView
                resultMsg.sendToTarget()

                return true
            }

            override fun onCloseWindow(window: WebView?) {
                super.onCloseWindow(window)
                rootElelement.removeView(window)
            }
        }
    }


    private fun String.changerAgent(): String{
        return this.replace("wv", " ")
    }

    @Throws(Exception::class)
    fun postMessageToWv(event: String?, data: JSONObject?) {
        val payload = JSONObject()
        payload.put("event", event)
        payload.put("data", data)
        val url = "javascript:window.postMessage(\"" + payload.toString()
            .replace("\"", "\\\"") + "\", '*');"
        val vw = this
        Log.d("First", url)
        vw.post { vw.loadUrl(url) }
    }


    inner class JsObject {
        @JavascriptInterface
        fun postMessage(data: String) {
            try {
                val jObject = JSONObject(data)
                val event = jObject.getString("event")
                if (event.equals("nuxtready", ignoreCase = true)) {
                    val registered = sp.getBoolean(Const.isRegistered, false)
                    postMessageToWv("cordova-ready", JSONObject())
                    if (!registered) {
                        val payload = JSONObject()
                        val token = "$gadid,$appsuid,$pushtokenuuid"
                        payload.put("token", token)
                        postMessageToWv("android-push-token", payload)
                        sp.edit().putBoolean(Const.isRegistered, true).apply()
                    }
                }
            } catch (e: java.lang.Exception) {
                Log.e("First", e.message, e)
            }
        }
    }
}