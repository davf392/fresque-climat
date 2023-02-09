package com.idplus.fresqueclimat.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class FreskViewPagerAdapter(fm: FragmentActivity, private val fragments: List<Fragment>) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}