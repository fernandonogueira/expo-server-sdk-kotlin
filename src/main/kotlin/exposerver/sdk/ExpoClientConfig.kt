package exposerver.sdk

data class ExpoClientConfig (val baseUrl: String = "https://exp.host", val concurrentRequestLimit: Int = 6)