package sk.vican.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

  fun createOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(UrlConst.CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
      .readTimeout(UrlConst.READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
      .addInterceptor(interceptor).build()
  }

  inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
      .baseUrl(url)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
  }

}
