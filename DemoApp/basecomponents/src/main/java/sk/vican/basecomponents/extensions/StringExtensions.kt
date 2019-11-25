package sk.vican.basecomponents.extensions

import java.security.*

fun String.stringToSHA1(): String {
  val stringBuilder = StringBuilder()

  MessageDigest.getInstance("SHA-1")
    .digest(this.toByteArray(charset("UTF-8")))
    .forEach { byte ->
      stringBuilder.append(String.format("%02X", byte))
    }

  return stringBuilder.toString().toLowerCase()
}



