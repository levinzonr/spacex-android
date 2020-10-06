package cz.levinzonr.spotistats.presentation.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController: NavController
        get() {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
            return navHostFragment.navController
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBarConfiguration =  AppBarConfiguration(navController.graph, drawerLayout)
        toolbar.init(navController)
        navigationView.init(navController)
    }


    private fun MaterialToolbar.init(navController: NavController) {
        this@MainActivity.setSupportActionBar(this)
        val appBarConfig = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun NavigationView.init(navController: NavController) {

        setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
            .apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
    }


}
