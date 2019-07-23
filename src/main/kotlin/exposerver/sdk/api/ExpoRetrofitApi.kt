package exposerver.sdk.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ExpoRetrofitApi {

    @Headers("Accepts: application/json")
    @POST("/--/api/v2/push/send")
    fun sendPushNotifications(@Body body: List<ExpoPushMessageBody>): Call<Any>

}