package sternbach.software.geniforandroid.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import app.familygem.Global
import sternbach.software.geniforandroid.CONSTANTS
import sternbach.software.geniforandroid.MainActivity
import sternbach.software.geniforandroid.R
import timber.log.Timber

class OnAuthorizedActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_authorized)
        Timber.d("Authorized.")
        if(intent.data != null) {
            val uri = intent.data!!
            val token = uri.getQueryParameter("access_token")
            Global.preferences.edit(true) { putString(CONSTANTS.SHARED_PREFERENCES_KEY_ACCESS_TOKEN, token) }
            Timber.d("token: $token, uri: $uri")
            startActivity(Intent(this, MainActivity::class.java))
        } else Timber.wtf("Supposed to have been authorized, but intent data was null!!")
    }
}