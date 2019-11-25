package sk.vican.demoapp.ui.downloadImage

import androidx.lifecycle.MutableLiveData
import sk.vican.basecomponents.extensions.*
import sk.vican.basecomponents.ui.*
import sk.vican.demoapp.R
import sk.vican.localstorage.tools.PreferencesTool
import sk.vican.network.datamodel.*

class DownloadViewModel(
  private val downloadImageDataModel: DownloadImageDataModel,
  private val preferencesTool: PreferencesTool
): BaseViewModel() {

  // region Companion Object

  companion object {
    private const val BODY_PREFIX = "username="
  }

  // endregion Companion Object

  // region Public Attributes

  val username: MutableLiveData<String> = MutableLiveData()
  val password: MutableLiveData<String> = MutableLiveData()
  val isShowImagesButton: MutableLiveData<Boolean> = MutableLiveData()

  // endregion Public Attributes

  // region Public Methods

  fun checkImagesVisibility() {
    isShowImagesButton.value = preferencesTool.imageBase64.isNotEmpty()
  }

  fun removeImage() {
    preferencesTool.clearSharedPreferences()
    isShowImagesButton.value = false
  }

  fun download(success: () -> Unit, failed: (Int) -> Unit) {
    if (isNotFilledSignIn()) {
      failed(R.string.download_no_filled_credential)
      checkImagesVisibility()
    } else {
      downloadImageDataModel.downloadImage(
        password.value!!.toString().stringToSHA1(),
        BODY_PREFIX.plus(username.value!!.toString())
      )
        .subscribe(
          {
            preferencesTool.imageBase64 = it.imageBase64
            isShowImagesButton.value = true
            success()
          },
          {
            checkImagesVisibility()
            failed(R.string.download_bad_credential)
          }
        )
        .disposeWith(compositeDisposable)
    }
  }

  // endregion Public Methods

  // region Private Methods

  private fun isNotFilledSignIn() = username.value.isNullOrEmpty()
    .or(password.value.isNullOrEmpty())

  // endregion Private Methods
}
