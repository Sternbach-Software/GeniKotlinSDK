package org.sternbach.software.genikotlinsdk.api

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class GeniApiClientTest {
    @Test
    fun testClientInitialization() = runTest {
        // Just verify we can instantiate it.
        // Real network calls are hard to mock without MockEngine in dependencies (which I didn't add explicitly, but ktor-client-mock is useful).
        val client = GeniApiClientV1("token")
        assertTrue(true)
    }
}
