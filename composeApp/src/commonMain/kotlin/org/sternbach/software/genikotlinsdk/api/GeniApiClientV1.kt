package org.sternbach.software.genikotlinsdk.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

class GeniApiClientV1(
    private val token: String
) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(token, "")
                }
            }
        }
    }

    suspend fun getProfile(): ProfileResponse {
        return client.get("https://www.geni.com/api/profile").body()
    }

    @Serializable
    data class ProfileResponse(
        val name: String,
        val id: String
    )
}
