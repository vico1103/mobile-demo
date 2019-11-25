package sk.vican.demoapp.ui.showImage

import sk.vican.basecomponents.ui.BaseViewModel
import sk.vican.localstorage.tools.PreferencesTool

class ShowImageViewModel(private val preferencesTool: PreferencesTool): BaseViewModel() {

  // region Public Methods

  fun getImageString() = preferencesTool.imageBase64

  fun viewModelCompositeDisposable() = compositeDisposable

  // endregion Public Methods
}
