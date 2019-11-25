package sk.vican.network.injection

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import sk.vican.network.Retrofit
import sk.vican.network.UrlConst
import sk.vican.network.api.*

val networkModule = module {
  single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
  single { Retrofit.createOkHttpClient(get()) }
  single { Retrofit.createWebService<DownloadImageService>(get(), UrlConst.SERVER_URL) }
}
