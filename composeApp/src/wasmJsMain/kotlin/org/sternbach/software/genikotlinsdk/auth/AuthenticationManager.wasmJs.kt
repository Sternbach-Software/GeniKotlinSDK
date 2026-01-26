package org.sternbach.software.genikotlinsdk.auth

import kotlinx.browser.window

actual object AuthenticationManager {
    actual fun startAuth() {
        val appId = "682"
        val redirectUri = window.location.href.split("#")[0]
        val url = "https://www.geni.com/platform/oauth/authorize?client_id=$appId&redirect_uri=$redirectUri&response_type=token&display=web"
        window.location.href = url
    }
     actual fun checkAuth() {
        val hash = window.location.hash
        if (hash.startsWith("#")) {
             val params = hash.substring(1).split("&").associate {
                val parts = it.split("=")
                if (parts.size == 2) parts[0] to parts[1] else "" to ""
            }
            val token = params["access_token"]
            if (token != null) {
                TokenStore.setToken(token)
                window.location.hash = ""
            }
        }
    }

    actual fun logout() {
        TokenStore.setToken(null)
    }
}
