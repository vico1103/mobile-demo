package sk.vican.demoapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(modules())
        }

    }


    // region Private Methods

    private fun modules() = listOf<Module>()

    // endregion Private Methods

}
