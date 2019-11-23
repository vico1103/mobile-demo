package sk.vican.basecomponents.extensions

import io.reactivex.disposables.CompositeDisposable

fun CompositeDisposable.disposeWith(compositeDisposable: CompositeDisposable) =
  this.apply {
    compositeDisposable.add(this)
  }
