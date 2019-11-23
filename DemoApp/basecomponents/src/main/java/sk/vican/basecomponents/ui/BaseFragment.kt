package sk.vican.basecomponents.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<TViewModel: BaseViewModel, TViewDataBinding: ViewDataBinding>:
  Fragment() {

  // region Protected Attributes

  protected val viewModel: TViewModel by lazy {
    getViewModel(viewModelClass())
  }

  protected lateinit var binding: TViewDataBinding private set

  // endregion Protected Attributes

  // region Protected Methods

  @LayoutRes
  protected abstract fun layoutId(): Int

  protected abstract fun viewModelClass(): KClass<TViewModel>

  protected abstract fun initializeView()

  // endregion Protected Methods

  // region Public Methods

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View =
    inflater.inflate(layoutId(), container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding = DataBindingUtil.bind(view)
      ?: throw IllegalArgumentException("Exception: BaseFragment not found View for binding")

    initializeView()
  }

  // endregion Public Methods

}
