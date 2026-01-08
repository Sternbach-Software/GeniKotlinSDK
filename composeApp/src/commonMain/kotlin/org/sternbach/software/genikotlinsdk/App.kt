package org.sternbach.software.genikotlinsdk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sternbach.software.genikotlinsdk.api.GeniApiClientV1
import org.sternbach.software.genikotlinsdk.auth.AuthenticationManager
import org.sternbach.software.genikotlinsdk.auth.TokenStore

@Composable
@Preview
fun App() {
    MaterialTheme {
        val token by TokenStore.token.collectAsState()
        var profileName by remember { mutableStateOf<String?>(null) }
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (token == null) {
                Button(onClick = { AuthenticationManager.startAuth() }) {
                    Text("Login with Geni")
                }
            } else {
                Text("Logged in!")
                Button(onClick = {
                     scope.launch {
                         try {
                             val client = GeniApiClientV1(token!!)
                             val profile = client.getProfile()
                             profileName = profile.name
                         } catch (e: Exception) {
                             profileName = "Error: ${e.message}"
                         }
                     }
                }) {
                    Text("Fetch Profile")
                }
                if (profileName != null) {
                    Text("Hello, $profileName")
                }
            }
        }
    }
}
