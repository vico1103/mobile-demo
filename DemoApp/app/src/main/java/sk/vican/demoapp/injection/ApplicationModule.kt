package sk.vican.demoapp.injection

import android.content.*
import org.koin.android.ext.koin.*
import org.koin.dsl.*

val applicationModule = module {
  single {
    androidContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE)
  }
}
