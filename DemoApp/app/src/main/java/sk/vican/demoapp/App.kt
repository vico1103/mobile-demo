package sk.vican.demoapp

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import sk.vican.demoapp.injection.*
import sk.vican.localstorage.injection.toolsModule
import sk.vican.network.injection.*

class App: Application() {

  // region Public Methods

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@App)
      modules(modules())
    }
  }

  // endregion Public Methods

  // region Private Methods

  private fun modules() = listOf(
    applicationModule,
    networkModule,
    dataModelModule,
    toolsModule,
    viewModelModule
  )

  // endregion Private Methods
}
