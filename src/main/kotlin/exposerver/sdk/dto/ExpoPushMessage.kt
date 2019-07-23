package exposerver.sdk.dto

import exposerver.sdk.api.ExpoPushMessageBody

data class ExpoPushMessage(val to: ExpoPushToken, val data: Any? = null, val title: String = "",
                           val subtitle: String = "", val body: String? = null, val sound: PushSound? = null,
                           val ttl: Int = 300, val expiration: Int = 300, val priority: String = "default",
                           val badge: Int = 1, val channelId: String = "Default") {


    fun toExpoPushMessageBody(): ExpoPushMessageBody {
        return ExpoPushMessageBody(to = to.token, data = data, title = title,
                subtitle = subtitle, body = body, sound = sound,
                ttl = ttl, expiration = expiration, priority = priority,
                badge = badge, channelId = channelId)
    }

}