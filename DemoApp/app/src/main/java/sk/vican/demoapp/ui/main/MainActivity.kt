package sk.vican.demoapp.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import sk.vican.basecomponents.ui.BaseActivity
import sk.vican.demoapp.R
import sk.vican.demoapp.databinding.ActivityMainBinding

class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {

  // region Private Attributes

  private val navigationController: NavController by lazy {
    findNavController(R.id.nav_host_fragment).apply {
      graph = navInflater.inflate(R.navigation.app_navigation).apply {
        startDestination = R.id.fragment_download_image
      }
    }
  }

  // endregion Private Attributes

  // region Protected Methods

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setupActionBarWithNavController(navigationController)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navigationController.navigateUp()
  }

  override fun onBackPressed() {
    if (!navigationController.navigateUp()) {
      finish()
    }
  }

  override fun layoutId() = R.layout.activity_main

  override fun viewModelClass() = MainViewModel::class

  // endregion Protected Methods
}
