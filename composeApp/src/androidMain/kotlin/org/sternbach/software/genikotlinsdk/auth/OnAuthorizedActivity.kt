package org.sternbach.software.genikotlinsdk.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity

class OnAuthorizedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        if (uri != null) {
            // The URL fragment contains the access_token: #access_token=...&expires_in=...
            // Android Uri usually parses query params, but this is a fragment.
            val fragment = uri.fragment
            if (fragment != null) {
                val params = fragment.split("&").associate {
                    val parts = it.split("=")
                    if (parts.size == 2) parts[0] to parts[1] else "" to ""
                }
                val token = params["access_token"]
                if (token != null) {
                    TokenStore.setToken(token)
                }
            }
        }

        // Return to main activity or close
        // Assuming MainActivity is the entry point
        // In a real app we might want to launch MainActivity via Intent
        // val mainIntent = Intent(this, MainActivity::class.java)
        // startActivity(mainIntent)
        finish()
    }
}
