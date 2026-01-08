package org.sternbach.software.genikotlinsdk

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.sternbach.software.genikotlinsdk.auth.CONSTANTS
import sternbach.software.geniforandroid.auth.RequestAuthorizationActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val token: String? = null//Global.preferences.getString(CONSTANTS.SHARED_PREFERENCES_KEY_ACCESS_TOKEN, null)
        if(token != null) {
//            Timber.d("Token wasn't null! Token: $token")
            setContent {
                App()
            }
        } else {
            startActivity(Intent(this, RequestAuthorizationActivity::class.java))
        }
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}