package com.idplus.fresqueclimat

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.idplus.fresqueclimat.adapter.FreskFragmentAdapter


class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // display the app bar from the layout
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)


        val adapter = FreskFragmentAdapter(this, supportFragmentManager)
        viewPager!!.adapter = adapter

        tabLayout!!.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })

        tabLayout!!.setupWithViewPager(viewPager)

        // creating tab icons
        tabLayout!!.getTabAt(0)?.icon = resources.getDrawable(R.drawable.ic_baseline_newspaper_24)
        tabLayout!!.getTabAt(1)?.icon = resources.getDrawable(R.drawable.ic_baseline_groups_24)
        tabLayout!!.getTabAt(2)?.icon = resources.getDrawable(R.drawable.ic_baseline_explore_24)
    }
}