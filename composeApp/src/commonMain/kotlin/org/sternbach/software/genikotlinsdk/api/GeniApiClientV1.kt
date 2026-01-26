package org.sternbach.software.genikotlinsdk.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

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

    private val baseUrl = "https://www.geni.com/api"

    suspend fun getProfile(): ProfileResponse {
        return client.get("$baseUrl/profile").body()
    }

    suspend fun get(path: String): JsonElement {
        return client.get("$baseUrl/$path").body()
    }

    suspend fun post(path: String, body: Any): JsonElement {
        return client.post("$baseUrl/$path") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    @Serializable
    data class ProfileResponse(
        val name: String,
        val id: String
    )
}
