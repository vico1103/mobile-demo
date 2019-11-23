package sk.vican.basecomponents.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<TViewModel: BaseViewModel, TViewBinding: ViewDataBinding>:
  AppCompatActivity() {

  // region Protected Attributes

  protected val binding: TViewBinding by lazy {
    DataBindingUtil.setContentView<TViewBinding>(this, layoutId())
  }

  protected val viewModel: TViewModel by lazy {
    getViewModel(viewModelClass())
  }

  // endregion Protected Attributes

  // region Protected Methods

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.executePendingBindings()
  }

  @LayoutRes
  abstract fun layoutId(): Int

  abstract fun viewModelClass(): KClass<TViewModel>

  // endregion Protected Methods

}
