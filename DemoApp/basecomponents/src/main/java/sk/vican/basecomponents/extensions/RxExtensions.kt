package sk.vican.basecomponents.extensions

import io.reactivex.disposables.*

fun Disposable.disposeWith(compositeDisposable: CompositeDisposable) =
  this.apply {
    compositeDisposable.add(this)
  }
