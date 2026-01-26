package org.sternbach.software.genikotlinsdk.auth

import platform.AuthenticationServices.ASWebAuthenticationSession
import platform.Foundation.NSURL
import platform.Foundation.NSError
import platform.AuthenticationServices.ASWebAuthenticationSessionPresentationContextProvidingProtocol
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import platform.darwin.NSObject

actual object AuthenticationManager {
    private var session: ASWebAuthenticationSession? = null

    actual fun startAuth() {
        val appId = "682"
        val authUrl = NSURL(string = "https://www.geni.com/platform/oauth/authorize?client_id=$appId&redirect_uri=geniforandroid://www.geniforandroid.sternbach.org/authorize&response_type=token&display=mobile")
        val callbackUrlScheme = "geniforandroid"

        val newSession = ASWebAuthenticationSession(
            uRL = authUrl,
            callbackURLScheme = callbackUrlScheme,
            completionHandler = { callbackUrl: NSURL?, error: NSError? ->
                session = null // Release session on completion
                if (callbackUrl != null) {
                    val fragment = callbackUrl.fragment
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
            }
        )

        val presentationContextProvider = object : NSObject(), ASWebAuthenticationSessionPresentationContextProvidingProtocol {
            override fun presentationAnchorForWebAuthenticationSession(session: ASWebAuthenticationSession): UIWindow {
                return UIApplication.sharedApplication.keyWindow ?: UIWindow()
            }
        }

        newSession.presentationContextProvider = presentationContextProvider
        newSession.start()
        session = newSession // Keep strong reference
    }

    actual fun checkAuth() {
        // iOS handles this via completion handler of session.
    }

    actual fun logout() {
        TokenStore.setToken(null)
    }
}
