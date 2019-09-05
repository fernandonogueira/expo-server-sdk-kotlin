package exposerver.sdk

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import exposerver.sdk.api.ExpoRetrofitApi
import exposerver.sdk.dto.ExpoPushMessage
import exposerver.sdk.dto.ExpoPushTicket
import exposerver.sdk.dto.ExpoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ExpoClient(private val config: ExpoClientConfig) {

    private val retrofit: ExpoRetrofitApi by lazy {
        initRetrofit()
    }

    private fun initRetrofit(): ExpoRetrofitApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return Retrofit.Builder()
                .baseUrl(config.baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build().create(ExpoRetrofitApi::class.java)
    }

    fun getPushNotificationReceipts() {

    }

    fun isExpoPushToken(token: String?): Boolean {

        if (token == null) {
            return false
        }

        val regex = "^[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}\$".toRegex(RegexOption.IGNORE_CASE)

        return when {
            (token.startsWith("ExponentPushToken[") || token.startsWith("ExpoPushToken[")) &&
                    token.endsWith("]") || regex.matchEntire(token) != null -> true
            else -> false
        }
    }

    fun sendPushNotifications(messages: List<ExpoPushMessage>): ExpoResponse<ExpoPushTicket> {

        val rawMessages = messages.map {
            it.toExpoPushMessageBody()
        }

        val response = retrofit.sendPushNotifications(rawMessages).execute()
        return response.body() ?: ExpoResponse(emptyList())
    }

}
