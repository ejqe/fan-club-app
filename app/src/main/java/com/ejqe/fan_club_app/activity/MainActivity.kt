package com.ejqe.fan_club_app.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.ejqe.fan_club_app.R
import com.ejqe.fan_club_app.databinding.ActivityMainBinding
import com.ejqe.fan_club_app.fragment.HomeFragment
import com.ejqe.fan_club_app.fragment.MemberListFragment
import com.ejqe.fan_club_app.fragment.MusicFragment
import com.ejqe.fan_club_app.fragment.ProfileFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting the Toolbar
        setSupportActionBar(binding.toolbar)


        //Set default fragment
        binding.bottomNav.setItemSelected(R.id.memberListFragment)

        //Assign Listener on Bottom Nav
        binding.bottomNav.setOnItemSelectedListener {
            when (it) {
                R.id.memberListFragment -> loadFragment(MemberListFragment())
                R.id.musicFragment -> loadFragment(MusicFragment())
                R.id.profileFragment -> loadFragment(ProfileFragment())
                else -> loadFragment(HomeFragment())
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_nav,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragment_container)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }


}