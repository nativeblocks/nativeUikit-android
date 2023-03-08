package io.nativeblocks.uikit.util

import android.util.Patterns
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL

fun String?.isHttpUrl(): Boolean {
    if (this.isNullOrEmpty()) return false

    var tempString = this.trim()
    if (this.startsWith("http").not()) {
        tempString = "https://$tempString"
    }
    return try {
        URL(tempString).toURI()
        Patterns.WEB_URL.matcher(tempString).matches()
    } catch (e: MalformedURLException) {
        e.printStackTrace()
        false
    } catch (e: URISyntaxException) {
        e.printStackTrace()
        false
    }
}