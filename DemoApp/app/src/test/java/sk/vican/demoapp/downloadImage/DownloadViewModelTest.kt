package sk.vican.demoapp.downloadImage

import androidx.arch.core.executor.testing.*
import io.reactivex.*
import io.reactivex.android.plugins.*
import io.reactivex.plugins.*
import io.reactivex.schedulers.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.*
import org.junit.rules.*
import org.junit.runner.*
import org.junit.runners.model.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*
import sk.vican.demoapp.*
import sk.vican.demoapp.ui.downloadImage.*
import sk.vican.localstorage.tools.*
import sk.vican.network.datamodel.*
import sk.vican.network.model.app.*

class DownloadViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @Rule
  @JvmField
  val mockitoRule = MockitoJUnit.rule()!!

  @Rule
  @JvmField
  val schedulers = RxImmediateSchedulerRule()

  private lateinit var preferencesTool: PreferencesTool
  private lateinit var downloadImageDataModel: DownloadImageDataModel

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    preferencesTool = mock(PreferencesTool::class.java)


    downloadImageDataModel = mock(DownloadImageDataModel::class.java)
  }

  @Test
  fun whenCorrectDataPass_thenReturnImageInBase64() {
    val downloadViewModel = DownloadViewModel(downloadImageDataModel, preferencesTool)


    downloadViewModel.password.postValue("test")
    downloadViewModel.username.postValue("password")

    `when`(downloadImageDataModel.downloadImage("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", "username=password"))
      .thenReturn(
        Single.just(Image("aa"))
      )

    val success: () -> Unit = {
      assert(true) { "success" }
    }

    val failed: (Int) -> Unit = {
      assertThat(it, `is`(R.string.download_no_filled_credential))
    }

    downloadViewModel.download(success, failed)
  }

  @Test
  fun whenBadCredentialGiven_thenReturnFailWithBadCredentialStringForToast() {
    val downloadViewModel = DownloadViewModel(downloadImageDataModel, preferencesTool)


    downloadViewModel.password.postValue("test")
    downloadViewModel.username.postValue("pass")

    `when`(preferencesTool.imageBase64).thenReturn("")

    `when`(downloadImageDataModel.downloadImage("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", "username=pass"))
      .thenReturn(
        Single.error { Exception("error http 401")}
      )

    val success: () -> Unit = {
      assert(true) { "success" }
    }

    val failed: (Int) -> Unit = {
      assertThat(it, `is`(R.string.download_bad_credential))
    }

    downloadViewModel.download(success, failed)
  }

  @Test
  fun whenNoSignInDataFilled_thenReturnFail() {
    val downloadViewModel = DownloadViewModel(downloadImageDataModel, preferencesTool)

    `when`(preferencesTool.imageBase64).thenReturn("")

    downloadViewModel.password.postValue("")
    downloadViewModel.username.postValue("")

    val success: () -> Unit = {
      assert(true) { "success" }
    }

    val failed: (Int) -> Unit = {
      assertThat(it, `is`(R.string.download_no_filled_credential))
    }

    downloadViewModel.download(success, failed)
  }


}
