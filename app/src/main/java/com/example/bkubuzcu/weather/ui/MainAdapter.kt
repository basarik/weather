package com.example.bkubuzcu.weather.ui

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.bkubuzcu.weather.ui.camera.CameraFragment
import com.example.bkubuzcu.weather.ui.dashboard.DashboardFragment
import com.example.bkubuzcu.weather.ui.setting.SettingFragment

/**
 * Created by bkubuzcu on 06/11/18.
 * this is MainAdapter.
 */
class MainAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    /**
     * fragments with viewPager
     */
    private val fragments = listOf(
        DashboardFragment(),
        CameraFragment(),
        SettingFragment()
    )

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

}