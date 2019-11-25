package sk.vican.localstorage.tools

import android.content.SharedPreferences

class PreferencesTool(private val sharedPreferences: SharedPreferences) {

  // region Private Constants

  private object SharedKeys {
    const val IMAGE_STRING_BASE = "image_string_base"
  }

  private object DefaultValues {
    const val NO_IMAGE = ""
  }

  // endregion Private Constants

  // region Public Methods

  fun clearSharedPreferences() {
    sharedPreferences.edit().clear().apply()
  }

  var imageBase64: String
    get() {
      return sharedPreferences.getString(SharedKeys.IMAGE_STRING_BASE, DefaultValues.NO_IMAGE)
        ?: DefaultValues.NO_IMAGE
    }
    set(imageString) = imageString.putString(SharedKeys.IMAGE_STRING_BASE)

  // endregion Public Methods

  // region Private Methods

  private fun String.putString(key: String) = sharedPreferences
    .edit()
    .putString(key, this)
    .apply()

  // endregion Private Methods
}
