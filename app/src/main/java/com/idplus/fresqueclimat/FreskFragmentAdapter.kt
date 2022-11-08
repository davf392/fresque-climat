package com.idplus.fresqueclimat

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FreskFragmentAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val TAB_ITEM_NEWS = 0
    val TAB_ITEM_SESSIONS = 1
    val TAB_ITEM_RESOURCES = 2

    val TOTAL_TABS = 3

    val TAB_LABEL_NEWS: String = "NEWS"
    val TAB_LABEL_SESSIONS: String = "SESSIONS"
    val TAB_LABEL_RESOURCES: String = "RESOURCES"

    override fun getCount(): Int {
        return TOTAL_TABS
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            TAB_ITEM_NEWS       -> { return NewsFragment() }
            TAB_ITEM_SESSIONS   -> { return SessionFragment() }
            TAB_ITEM_RESOURCES  -> { return ResourcesFragment() }
            else -> {
                return NewsFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            TAB_ITEM_NEWS -> TAB_LABEL_NEWS
            TAB_ITEM_SESSIONS -> TAB_LABEL_SESSIONS
            TAB_ITEM_RESOURCES -> TAB_LABEL_RESOURCES
            else -> {
                return TAB_LABEL_NEWS
            }
        }
    }
}