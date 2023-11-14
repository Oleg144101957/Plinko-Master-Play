package app.plinko.masterplay.data

import android.content.Context
import android.util.Log
import app.plinko.masterplay.Const
import app.plinko.masterplay.vm.NavigationViewModel
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DataManager(private val context: Context, private val navigationViewModel: NavigationViewModel) {

    private val sp = context.getSharedPreferences(Const.SP, Context.MODE_PRIVATE)


    suspend fun initDataManager(){
        val currentData = sp.getString(Const.CURRENT_DATA, Const.EMPTY) ?: Const.EMPTY

        if (currentData == Const.EMPTY){

            val fb = getFacebook(context)
            val apps = getApps(context)
            val gadid = getGadid(context)

            Log.d("123123", "apps ${apps.toString()}")
            Log.d("123123", "gadid $gadid")
            Log.d("123123", "fb $fb")

            //get apps and fb and add it to VM

            createLink(fb, apps, gadid)


        } else {
            navigationViewModel.addData(currentData)
        }

    }

    private fun createLink(fb: String, apps: MutableMap<String, Any>?, gadid: String) {

        Log.d("123123", "createLink method")

        OneSignal.setExternalUserId(gadid)


        val listOfFacebookSubs : List<String> = fb.substringAfter("://")
            .split('_')
            .filter { it.startsWith("sub") }
            .map { it.substringAfter("=") }

        val af_channel: String = apps?.getOrDefault(Const.AF_CHANNEL, "null").toString()
        val adset: String = apps?.getOrDefault(Const.ADSET, "null").toString()
        val media_source: String = apps?.getOrDefault(Const.MEDIA_SOURCE, "null").toString()
        val af_status: String = apps?.getOrDefault(Const.AF_STATUS, "null").toString()
        val af_ad: String = apps?.getOrDefault(Const.AF_AD, "null").toString()
        val campaign_id: String = apps?.getOrDefault(Const.CAMPAIGN_ID, "null").toString()
        val adset_id: String = apps?.getOrDefault(Const.ADSET_ID, "null").toString()
        val ad_id: String = apps?.getOrDefault(Const.AD_ID, "null").toString()

        val sub1 = listOfFacebookSubs.getOrNull(0) ?: "null"
        OneSignal.sendTag("sub1", sub1)

        val sub2 = if (listOfFacebookSubs.getOrNull(1) != null) listOfFacebookSubs.getOrNull(1) else "LGxfTPfW"

        val linkList = listOf("https://f", "t-apps")


        if (!checkOrganicInstall(af_status, sub2!!)){

            //Take second sub

            val linkBuilder = StringBuilder("${linkList[0]}${linkList[1]}.com/$sub2?")
            linkBuilder.append("af_channel=$af_channel&")
            linkBuilder.append("adset=$adset&")
            linkBuilder.append("media_source=$media_source&")
            linkBuilder.append("af_status=$af_status&")
            linkBuilder.append("af_ad=$af_ad&")
            linkBuilder.append("campaign_id=$campaign_id&")
            linkBuilder.append("adset_id=$adset_id&")
            linkBuilder.append("ad_id=$ad_id&")
            linkBuilder.append("sub3=${listOfFacebookSubs.getOrNull(2)}&")
            linkBuilder.append("sub4=${listOfFacebookSubs.getOrNull(3)}&")
            linkBuilder.append("sub5=${listOfFacebookSubs.getOrNull(4)}&")
            linkBuilder.append("sub6=${listOfFacebookSubs.getOrNull(5)}&")
            linkBuilder.append("sub7=${listOfFacebookSubs.getOrNull(6)}&")
            linkBuilder.append("sub8=${listOfFacebookSubs.getOrNull(7)}&")
            linkBuilder.append("sub9=${listOfFacebookSubs.getOrNull(8)}&")
            linkBuilder.append("sub10=${listOfFacebookSubs.getOrNull(9)}")

            navigationViewModel.addData(linkBuilder.toString())
        }

    }

    private fun checkOrganicInstall(afStatus: String, sub2: String) : Boolean {
        Log.d("123123", "Status is $afStatus")

        if (afStatus == "Organic"){
            sp.edit().putString(Const.CURRENT_DATA, Const.WARNING).apply()
        }

        return afStatus == "Organic" && sub2 == "LGxfTPfW"
    }

    private suspend fun getApps(context: Context) : MutableMap<String, Any>? = suspendCoroutine {continuation ->

        AppsFlyerLib.getInstance().init(
            "DHRhPVPg6Vok7RerAiuSh5",
            object : AppsFlyerConversionListener {

                override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                    Log.d("123123", "onConversionDataSuccess $data")
                    continuation.resume(data)

                }

                override fun onConversionDataFail(p0: String?) {
                    continuation.resume(null)
                }

                override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
                    continuation.resume(null)
                }

                override fun onAttributionFailure(p0: String?) {
                    continuation.resume(null)
                }
            }, context
        ).start(context)
    }

    private suspend fun getGadid(context: Context) : String = withContext(Dispatchers.IO){
        val gadid = AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()
        sp.edit().putString(Const.GAID, gadid).apply()
        gadid
    }

    private suspend fun getFacebook(context: Context) : String = suspendCoroutine{ cont ->
        FacebookSdk.setApplicationId("140903979057173")
        FacebookSdk.setClientToken("ca98a3c02f0e1af5f096a5f9ba147e4b")
        FacebookSdk.sdkInitialize(context)

        AppLinkData.fetchDeferredAppLinkData(context){
            cont.resume(it?.targetUri.toString())
        }
    }
}