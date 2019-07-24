package exposerver.sdk.dto

import exposerver.sdk.api.ExpoPushMessageBody

data class ExpoPushMessage(val to: ExpoPushToken, val data: Any? = null, val title: String? = null,
                           val subtitle: String? = null, val body: String? = null, val sound: PushSound? = null,
                           val ttl: Int? = 300, val expiration: Int? = null, val priority: String = "default",
                           val badge: Int = 1, val channelId: String? = null) {


    fun toExpoPushMessageBody(): ExpoPushMessageBody {
        return ExpoPushMessageBody(to = to.token, data = data, title = title,
                subtitle = subtitle, body = body, sound = sound,
                ttl = ttl, expiration = expiration, priority = priority,
                badge = badge, channelId = channelId)
    }

}