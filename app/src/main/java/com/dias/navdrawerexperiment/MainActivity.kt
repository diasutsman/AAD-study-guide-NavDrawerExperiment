package com.dias.navdrawerexperiment

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.dias.navdrawerexperiment.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _appBarConfiguration: AppBarConfiguration? = null
    private val appBarConfiguration get() = _appBarConfiguration as AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        _appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)

        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setNavigationItemSelectedListener {
            Toast.makeText(
                this,
                mapOf(R.id.action_gallery to "Gallery",
                    R.id.action_import to "Import",
                    R.id.action_slideshow to "Slideshow",
                    R.id.action_tools to "Tools")[it.itemId] ?: "",
                Toast.LENGTH_SHORT
            ).show()

            // This is for maintaining the navigation behaviour of the navigation view
            NavigationUI.onNavDestinationSelected(it, navController)
            // This will close the drawer
            binding.root.closeDrawer(GravityCompat.START)

            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}