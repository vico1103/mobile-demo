package sk.vican.network.api

import io.reactivex.*
import okhttp3.*
import retrofit2.http.*
import retrofit2.http.Headers
import sk.vican.network.*
import sk.vican.network.model.network.*

interface DownloadImageService {

  @POST(UrlConst.DOWNLOAD_IMAGE_PATH)
  @Headers("Content-Type:application/x-www-form-urlencoded")
  fun downloadImage(
    @Header("Authorization") passwordSHA1: String,
    @Body body: RequestBody
  ): Single<ImageResponse>

}
