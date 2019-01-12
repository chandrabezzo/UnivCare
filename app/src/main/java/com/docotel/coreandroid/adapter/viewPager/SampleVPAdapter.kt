package com.docotel.coreandroid.adapter.viewPager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout

/**
 * Created by bezzo on 11/01/18.
 */
class SampleVPAdapter constructor(var context: Context,
                                  var fragmentManager : FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    var tabs : ArrayList<Fragment> = ArrayList()
    lateinit var tabsTitle : ArrayList<String>
    var imagesActiveTab : ArrayList<Int> = ArrayList()
    var imagesInactiveTab : ArrayList<Int> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return tabs[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabsTitle[position]
    }

    override fun getCount(): Int {
        return tabs.size
    }

    fun addTab(fragment: Fragment, title : String, inactiveImage : Int, activeImage : Int){
        tabs.add(fragment)
        tabsTitle.add(title)
        imagesInactiveTab.add(inactiveImage)
        imagesActiveTab.add(activeImage)
    }

    fun getInactiveImage(position: Int) : Int {
        return imagesInactiveTab[position]
    }

    fun getActiveImage(position: Int) : Int {
        return imagesActiveTab[position]
    }

    fun changeIconTab(tabLayout: TabLayout, position: Int){
        for (counter in 0 until tabs.size){
            if (counter == position){
                tabLayout.getTabAt(counter)!!.setIcon(getActiveImage(counter))
            }
            else {
                tabLayout.getTabAt(counter)!!.setIcon(getInactiveImage(counter))
            }
        }
    }
}