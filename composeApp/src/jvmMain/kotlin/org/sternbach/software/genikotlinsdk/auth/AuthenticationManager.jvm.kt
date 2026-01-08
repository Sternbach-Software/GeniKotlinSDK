package org.sternbach.software.genikotlinsdk.auth

import java.awt.Desktop
import java.net.URI

actual object AuthenticationManager {
    actual fun startAuth() {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            val appId = "682"
            val url = "https://www.geni.com/platform/oauth/authorize?client_id=$appId&redirect_uri=geniforandroid://www.geniforandroid.sternbach.org/authorize&response_type=token&display=web"
            Desktop.getDesktop().browse(URI(url))
        }
    }

    actual fun checkAuth() {
        // Desktop needs a way to receive the token.
        // Currently manual paste or local server is needed.
    }

    actual fun logout() {
        TokenStore.setToken(null)
    }
}
