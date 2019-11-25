package sk.vican.localstorage.injection

import org.koin.dsl.module
import sk.vican.localstorage.tools.PreferencesTool

val toolsModule = module {
  single { PreferencesTool(get()) }
}
