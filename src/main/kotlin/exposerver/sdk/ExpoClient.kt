package exposerver.sdk

import exposerver.sdk.api.ExpoRetrofitApi
import exposerver.sdk.dto.ExpoPushMessage
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

        return Retrofit.Builder()
                .baseUrl(config.baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
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

    fun sendPushNotifications(messages: List<ExpoPushMessage>) {

        val rawMessages = messages.map {
            it.toExpoPushMessageBody()
        }

        val response = retrofit.sendPushNotifications(rawMessages).execute()

        println(response)

        // Send

        // Handle errors

    }

}