package sk.vican.demoapp.ui.downloadImage

import android.view.*
import android.widget.*
import androidx.core.widget.*
import androidx.navigation.fragment.*
import sk.vican.basecomponents.ui.*
import sk.vican.demoapp.R
import sk.vican.demoapp.databinding.*

class DownloadFragment: BaseFragment<DownloadViewModel, FragmentDownloadImageBinding>() {

  // region Private Lambdas

  private val downloadSuccess: () -> Unit = {
    binding.apply {
      loadingProgressBar.visibility = View.GONE
      downloadImageButton.visibility = View.VISIBLE
      this@DownloadFragment.viewModel.checkImagesVisibility()
    }
  }

  private val downloadFailed: (Int) -> Unit = { stringId ->
    binding.apply {
      loadingProgressBar.visibility = View.GONE
      downloadImageButton.visibility = View.VISIBLE
    }
    Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show()
  }

  // endregion Private Lambdas

  // region Protected Methods

  override fun layoutId() = R.layout.fragment_download_image

  override fun viewModelClass() = DownloadViewModel::class

  override fun initializeView() {

    binding.viewModel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner


    binding.showImageButton.setOnClickListener {
      findNavController().navigate(R.id.action_fragment_log_in_to_fragment_registration)
    }

    viewModel.checkImagesVisibility()

    binding.apply {
      downloadImageButton.setOnClickListener {
        it.visibility = View.INVISIBLE
        showImageButton.visibility = View.GONE
        removeImageButton.visibility = View.GONE
        loadingProgressBar.visibility = View.VISIBLE
        this@DownloadFragment.viewModel.download(downloadSuccess, downloadFailed)
      }

    }

    binding.usernameEditTextInput.doAfterTextChanged {
      viewModel.username.value = it.toString()
    }

    binding.passwordEditTextInput.doAfterTextChanged {
      viewModel.password.value = it.toString()
    }

    binding.removeImageButton.setOnClickListener {
      viewModel.removeImage()
    }
  }

  // endregion Protected Methods
}
