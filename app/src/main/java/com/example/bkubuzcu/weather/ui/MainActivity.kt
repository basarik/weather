package com.example.bkubuzcu.weather.ui

import android.support.v4.view.ViewPager
import com.example.bkubuzcu.weather.R
import com.example.bkubuzcu.weather.base.BaseActivity
import com.example.bkubuzcu.weather.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    /**
     * main adapter
     */
    private lateinit var adapter: MainAdapter

    /**
     * dashboard fragment id
     */
    private val fragmentDashboard = 0
    /**
     * camera fragment id
     */
    private val fragmentCamera = 1
    /**
     * setting fragment id
     */
    private val fragmentSetting = 2

    override fun layoutResource() = R.layout.activity_main

    override fun initActivity() {
        adapter = MainAdapter(supportFragmentManager)

        vPFragment.adapter = adapter
        vPFragment.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true

                when (position) {
                    fragmentDashboard -> {
                        getActivityTitle(fragmentDashboard)
                        refreshWeather()
                    }
                    fragmentCamera -> getActivityTitle(fragmentCamera)
                    fragmentSetting -> getActivityTitle(fragmentSetting)
                }
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mainPage -> {
                    vPFragment.currentItem = fragmentDashboard
                    getActivityTitle(fragmentDashboard)
                    refreshWeather()
                }

                R.id.camera -> {
                    vPFragment.currentItem = fragmentCamera
                    getActivityTitle(fragmentCamera)
                }

                R.id.setting -> {
                    vPFragment.currentItem = fragmentSetting
                    getActivityTitle(fragmentSetting)
                }
            }
            true
        }
    }

    /**
     * decides the activity title.
     */
    private fun getActivityTitle(fragment: Int) {
        when (fragment) {
            fragmentDashboard -> setTitle(R.string.mainPage)
            fragmentCamera -> setTitle(R.string.camera)
            else -> setTitle(R.string.setting)
        }
    }

    /**
     * refresh weather
     */
    private fun refreshWeather() {
        (adapter.getItem(fragmentDashboard) as DashboardFragment?)?.refresh()
    }
}
