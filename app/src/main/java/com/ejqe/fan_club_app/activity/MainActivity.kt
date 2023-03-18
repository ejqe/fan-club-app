package com.ejqe.fan_club_app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNav.setOnItemSelectedListener {
            when (it) {
                R.id.memberListFragment -> loadFragment(MemberListFragment())
                R.id.musicFragment -> loadFragment(MusicFragment())
                R.id.profileFragment -> loadFragment(ProfileFragment())
                else -> loadFragment(HomeFragment())
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }


}