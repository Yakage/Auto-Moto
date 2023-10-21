package com.example.auto_moto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.auto_motov04.DBhelper
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView
    private lateinit var listener: NavController.OnDestinationChangedListener
    private lateinit var db: DBhelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.navigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView.setupWithNavController(navController)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.loginFragment) {
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.signUpFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.forgotPasswordFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.servicesFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.appointmentFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.receiptFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.editProfileFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.myCarsFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.myAddressFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.aboutUsFragment){
                toolbar.visibility = View.GONE
                navigationView.visibility = View.GONE
            }
            else if(destination.id == R.id.myServicesFragment){
                toolbar.title = "Transaction"
            }
            else if(destination.id == R.id.accountFragment){
                toolbar.title = "Account"
            }
            else {
                toolbar.visibility = View.VISIBLE
                navigationView.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()

    }
    override fun onBackPressed() {
        super.onBackPressed()
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            onBackPressedDispatcher.onBackPressed()
        }
    }

}