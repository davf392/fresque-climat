package com.idplus.fresqueclimat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.idplus.fresqueclimat.adapter.FreskViewPagerAdapter
import com.idplus.fresqueclimat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listOfFragments = listOf(NewsFragment(), SessionFragment(), ResourcesFragment())
        binding.mainViewPager.adapter = FreskViewPagerAdapter(this, listOfFragments)
        binding.mainViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    0 -> binding.bottomNavigationView.menu.findItem(R.id.miNews).isChecked = true
                    1 -> binding.bottomNavigationView.menu.findItem(R.id.miSessions).isChecked = true
                    2 -> binding.bottomNavigationView.menu.findItem(R.id.miResources).isChecked = true
                }
            }
        })

        // Listen bottom navigation tabs change
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miNews -> binding.mainViewPager.setCurrentItem(0, true)
                R.id.miSessions -> binding.mainViewPager.setCurrentItem(1, true)
                R.id.miResources -> binding.mainViewPager.setCurrentItem(2, true)
            }
            true
        }
    }
}