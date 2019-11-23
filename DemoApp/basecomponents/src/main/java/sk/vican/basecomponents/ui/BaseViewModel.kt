package sk.vican.basecomponents.ui

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

  // region Protected Attributes

  protected val compositeDisposable: CompositeDisposable by lazy {
    CompositeDisposable()
  }

  // endregion Protected Attributes

  // region Protected Methods

  override fun onCleared() {
    super.onCleared()

    compositeDisposable.dispose()
  }

  // endregion Protected Methods
}
