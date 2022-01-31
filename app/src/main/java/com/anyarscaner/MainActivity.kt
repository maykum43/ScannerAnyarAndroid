package com.anyarscaner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.anyarscaner.helper.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.anyarscaner.activity.MasukActivity
import com.anyarscaner.fragment.ProfilFragment
import com.anyarscaner.fragment.ScannerFragment
import com.anyarscaner.fragment.SupportFragment

class MainActivity : AppCompatActivity() {

    private val fragmentScanner : Fragment = ScannerFragment()
    private val fragmentSupport : Fragment = SupportFragment()
    private val fragmentProfil : Fragment = ProfilFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active : Fragment = fragmentProfil

    private lateinit var menu : Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private val statusLogin = false

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        s = SharedPref(this)

        setUpBottomNav()
    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentProfil).show(fragmentProfil).commit()
        fm.beginTransaction().add(R.id.container, fragmentScanner).hide(fragmentScanner).commit()
        fm.beginTransaction().add(R.id.container, fragmentSupport).hide(fragmentSupport).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.navigation_scanner ->{
                    callFragment(0,fragmentProfil)
                }
                R.id.navigation_support ->{
                    callFragment(1,fragmentScanner)
                }
                R.id.navigation_profil ->{
                    if (s.getStatusLogin()){
                        callFragment(2,fragmentSupport)
                    }else{
                        startActivity(Intent(this, MasukActivity::class.java))
                    }

                }
            }

            false
        }
    }

    fun callFragment(int: Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}