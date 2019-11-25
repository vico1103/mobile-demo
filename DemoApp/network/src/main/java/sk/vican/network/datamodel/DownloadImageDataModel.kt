package sk.vican.network.datamodel

import io.reactivex.android.schedulers.*
import io.reactivex.schedulers.*
import okhttp3.*
import sk.vican.network.api.DownloadImageService
import sk.vican.network.model.app.*

class DownloadImageDataModel(private val downloadImageApi: DownloadImageService) {

  // region Public Methods

  fun downloadImage(authorization: String, username: String) = downloadImageApi
    .downloadImage(authorization, requestedBody(username))
    .map { response -> Image(response.image) }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

  // endregion Public Methods

  // region Private Methods

  private fun requestedBody(username: String) =
    RequestBody.create(MediaType.parse("text/plain"), username)

  // endregion Private Methods
}
