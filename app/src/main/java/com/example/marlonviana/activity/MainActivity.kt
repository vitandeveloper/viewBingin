package com.example.marlonviana.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marlonviana.R
import com.example.marlonviana.databinding.ActivityMainBinding
import com.example.marlonviana.fragments.FragmentFav
import com.example.marlonviana.fragments.FragmentHome
import com.example.marlonviana.util.TAG_FAV
import com.example.marlonviana.util.TAG_HOME
import com.example.marlonviana.util.disableShiftMode
import com.example.marlonviana.util.justTry



class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    private var fragmentHome = FragmentHome()
    private var fragmentFav = FragmentFav()

    //region VIEW
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        goToHome()
        initBootomNavigation()
    }

    private fun initBootomNavigation() {
        justTry {
            binding.bottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId){
                    R.id.menu_home -> {
                       goToHome()
                    }
                    R.id.fmenu_fav -> {
                        goToFav()
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }
            binding.bottomNavigation.disableShiftMode()
        }
    }
    //endregion

    //region NAVIGATION
    private fun goToHome(){
        supportFragmentManager.beginTransaction().apply {
            if (fragmentHome.isAdded) {
                show(fragmentHome)
            } else {
                replace(R.id.fragment_home, fragmentHome, TAG_HOME)
            }

            supportFragmentManager.fragments.forEach {
                if (it != fragmentHome && it.isAdded) {
                   hide(it)
                }
            }
        }.commit()
    }

    private fun goToFav(){
        supportFragmentManager.beginTransaction().apply {
            if (fragmentFav.isAdded) {
                show(fragmentFav)
            } else {
                add(R.id.fragment_home, fragmentFav, TAG_FAV)
            }

            supportFragmentManager.fragments.forEach {
                if (it != fragmentFav && it.isAdded)
                {
                    hide(it)
                }
            }
        }.commit()
    }
    //endregion
}