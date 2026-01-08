package sternbach.software.geniforandroid.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import app.familygem.Global
import sternbach.software.geniforandroid.CONSTANTS
import timber.log.Timber

class OnDeAuthorizedActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Deauthorized.")
        Global.preferences.edit(true) { remove(CONSTANTS.SHARED_PREFERENCES_KEY_ACCESS_TOKEN) }
    }
}