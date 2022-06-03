package com.anyarscaner

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.anyarscaner.activity.LoginActivity
import com.anyarscaner.activity.ScanBarcodeActivity
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.databinding.ActivityMainBinding
import com.anyarscaner.fragment.HomeFragment
import com.anyarscaner.fragment.ProfilFragment
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private val menuScan : Activity = ScannerFragment(
    val fragmentSupport : Fragment = ProfilFragment()
    val fragmentHome : Fragment = HomeFragment()
    val fm: FragmentManager = supportFragmentManager
    var active : Fragment = fragmentHome

    private lateinit var menu : Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        s = SharedPref(this)
        setUpBottomNav()
        setPoin()
        setJmlRedeem()
    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentSupport).hide(fragmentSupport).commit()

        bottomNavigationView = binding.navView
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_scanner ->{
                    if (s.getStatusLogin()){
                        val intent = Intent(this, ScanBarcodeActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    }

                }

                R.id.navigation_profile -> {
                    if (s.getStatusLogin()){
                        callFragment(2, fragmentSupport)
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
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

    private var exitTime: Long = 0
    override fun onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 8000) {
            Toast.makeText(this, "Klik kembali untuk keluar", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
//        super.onBackPressed()
    }

    fun setPoin(){
        ApiConfig.instanceRetrofit.totalPoin(s.getString(s.email)).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                s.setString(s.total_poin, respon.total_poin.toString())
//                Toast.makeText(this@RiwayatActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Terjadi Kesalahan dalam poin anda : "+t.message, Toast.LENGTH_SHORT).show()
            }
        }).toString()
    }

    fun setJmlRedeem(){
        ApiConfig.instanceRetrofit.jumlah(s.getString(s.email)).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                s.setString(s.jumlah, respon.jumlah.toString())
                s.setString(s.statusReed, respon.ststusReed.toString())
//                Toast.makeText(this@RiwayatActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Terjadi Kesalahan dalam poin anda : "+t.message, Toast.LENGTH_SHORT).show()
            }
        }).toString()
    }
}