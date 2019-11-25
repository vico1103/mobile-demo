package sk.vican.demoapp.ui.showImage

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sk.vican.basecomponents.extensions.disposeWith
import sk.vican.basecomponents.ui.BaseFragment
import sk.vican.demoapp.R
import sk.vican.demoapp.databinding.FragmentShowImageBinding


class ShowImageFragment : BaseFragment<ShowImageViewModel, FragmentShowImageBinding>() {

    // region Protected Methods

    override fun layoutId() = R.layout.fragment_show_image

    override fun viewModelClass() = ShowImageViewModel::class


    override fun initializeView() {

        getBitMapSingle(viewModel.getImageString())
            .subscribe(
                { bitmapImage -> binding.downloadedImageView.setImageBitmap(bitmapImage) },
                { error -> Log.e("MY tag", error.message ?: "ERROR") })
            .disposeWith(viewModel.viewModelCompositeDisposable())

    }

    // endregion Protected Methods

    // region Private Methods

    private fun getBitMapSingle(imageBase64: String) = Single.fromCallable {
        val imageBytes = Base64.decode(imageBase64, Base64.DEFAULT)

        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    // endregion Private Methods
}
