package sternbach.software.geniforandroid.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class RequestAuthorizationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Requesting auth")
        val appId = "682"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.geni.com/platform/oauth/authorize?client_id=$appId&redirect_uri=${Uri.encode("geniforandroid://www.geniforandroid.sternbach.org/authorize")}&response_type=token&display=mobile"))
        startActivity(browserIntent)
    }
}