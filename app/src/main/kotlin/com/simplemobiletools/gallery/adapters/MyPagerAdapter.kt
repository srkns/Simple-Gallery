package com.simplemobiletools.gallery.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import com.simplemobiletools.gallery.activities.ViewPagerActivity
import com.simplemobiletools.gallery.fragments.PhotoFragment
import com.simplemobiletools.gallery.fragments.VideoFragment
import com.simplemobiletools.gallery.fragments.ViewPagerFragment
import com.simplemobiletools.gallery.helpers.MEDIUM
import com.simplemobiletools.gallery.models.Medium

class MyPagerAdapter(val activity: ViewPagerActivity, fm: FragmentManager, val media: MutableList<Medium>) : FragmentStatePagerAdapter(fm) {
    private val mFragments = SparseArray<ViewPagerFragment>()
    override fun getCount() = media.size

    override fun getItem(position: Int): Fragment {
        val medium = media[position]
        val bundle = Bundle()
        bundle.putSerializable(MEDIUM, medium)
        val fragment: ViewPagerFragment

        if (medium.isVideo) {
            fragment = VideoFragment()
        } else {
            fragment = PhotoFragment()
        }

        fragment.arguments = bundle
        fragment.listener = activity
        return fragment
    }

    override fun getItemPosition(item: Any?) = PagerAdapter.POSITION_NONE

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as ViewPagerFragment
        mFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        mFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    fun getCurrentFragment(position: Int) = mFragments.get(position)
}
