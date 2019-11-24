package sk.vican.demoapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import sk.vican.demoapp.R

class MainActivity : AppCompatActivity() {

    // region Private Attributes

    private val navigationController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    // endregion Private Attributes

    // region Public Methods

    override fun onSupportNavigateUp() = navigationController.navigateUp()

    // endregion Public Methods

    // region Protected Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(navigationController)
    }

    // endregion Protected Methods
}
