package org.sternbach.software.genikotlinsdk.auth

import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference

actual object AuthenticationManager {
    private var contextRef: WeakReference<Context>? = null

    fun init(context: Context) {
        contextRef = WeakReference(context)
    }

    actual fun startAuth() {
        val context = contextRef?.get()
        if (context != null) {
            val intent = Intent(context, RequestAuthorizationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else {
            println("AuthenticationManager: Context not initialized")
        }
    }

    actual fun checkAuth() {
        // Android handles this via OnAuthorizedActivity which sets the token.
        // No explicit check needed on start if persisted, but we are using in-memory for now.
    }

    actual fun logout() {
        TokenStore.setToken(null)
    }
}
