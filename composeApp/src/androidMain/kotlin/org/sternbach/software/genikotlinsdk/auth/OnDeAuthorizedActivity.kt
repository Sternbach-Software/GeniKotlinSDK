package sternbach.software.geniforandroid.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.edit
import org.sternbach.software.genikotlinsdk.auth.CONSTANTS

class OnDeAuthorizedActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Timber.d("Deauthorized.")
//        Global.preferences.edit(true) { remove(CONSTANTS.SHARED_PREFERENCES_KEY_ACCESS_TOKEN) }
    }
}