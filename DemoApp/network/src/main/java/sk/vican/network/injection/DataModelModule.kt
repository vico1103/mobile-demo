package sk.vican.network.injection

import org.koin.dsl.*
import sk.vican.network.datamodel.*

val dataModelModule = module {
  single { DownloadImageDataModel(get()) }
}
