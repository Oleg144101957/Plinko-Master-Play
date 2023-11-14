package app.plinko.masterplay

import android.app.Application
import com.onesignal.OneSignal

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this)
        OneSignal.setAppId("3e556775-0af9-4264-be88-18ac6bf2f02a")
    }
}