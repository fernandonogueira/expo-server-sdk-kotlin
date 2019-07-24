package exposerver.sdk

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KotlinExpoClientTests {

    @Test
    fun isExpoPushTokenRegexTest() {
        val token = "n8wun033-u70m-0zgd-fz1c-7j8y4qtfno8o"

        val expo = ExpoClient(ExpoClientConfig())
        Assertions.assertTrue(expo.isExpoPushToken(token))

    }

    @Test
    fun isNotExpoPushTokenRegexTest() {
        val token = "n8wun033-u70m-0zgd-fz122c-7j8y4qtfno8o"

        val expo = ExpoClient(ExpoClientConfig())
        Assertions.assertFalse(expo.isExpoPushToken(token))
    }

    @Test
    fun isExpoPushTokenTest() {
        val token = "ExpoPushToken[n8wun033-u70m-0zgd-fz122c-7j8y4qtfno8o]"

        val expo = ExpoClient(ExpoClientConfig())
        Assertions.assertTrue(expo.isExpoPushToken(token))
    }

}
