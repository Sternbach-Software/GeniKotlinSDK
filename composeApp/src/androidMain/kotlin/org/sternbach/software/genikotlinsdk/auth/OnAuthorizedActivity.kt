package sternbach.software.geniforandroid.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.edit
import org.sternbach.software.genikotlinsdk.auth.CONSTANTS
//import timber.log.Timber

class OnAuthorizedActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_on_authorized) //just a static textview
//        Timber.d("Authorized.")
        if(intent.data != null) {
            val uri = intent.data!!
            val token = uri.getQueryParameter("access_token")
//            Global.preferences.edit(true) { putString(CONSTANTS.SHARED_PREFERENCES_KEY_ACCESS_TOKEN, token) }
//            Timber.d("token: $token, uri: $uri")
//            startActivity(Intent(this, MainActivity::class.java))
        } else {}//Timber.wtf("Supposed to have been authorized, but intent data was null!!")
    }
}