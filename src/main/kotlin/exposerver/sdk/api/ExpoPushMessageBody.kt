package exposerver.sdk.api

import exposerver.sdk.dto.PushSound

data class ExpoPushMessageBody(val to: String, val data: Any?, val title: String?,
                               val subtitle: String?, val body: String?, val sound: PushSound?,
                               val ttl: Int?, val expiration: Int?, val priority: String?,
                               val badge: Int?, val channelId: String?)