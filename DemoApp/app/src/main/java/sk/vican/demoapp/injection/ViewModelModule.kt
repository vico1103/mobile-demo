package sk.vican.demoapp.injection

import org.koin.androidx.viewmodel.dsl.*
import org.koin.dsl.module
import sk.vican.demoapp.ui.downloadImage.*
import sk.vican.demoapp.ui.main.*
import sk.vican.demoapp.ui.showImage.ShowImageViewModel

val viewModelModule = module {
  viewModel { DownloadViewModel(get(), get()) }
  viewModel { MainViewModel() }
  viewModel { ShowImageViewModel(get()) }
}
