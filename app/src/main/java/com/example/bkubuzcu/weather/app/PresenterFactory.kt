package com.example.bkubuzcu.weather.app

import android.content.Context
import com.example.bkubuzcu.weather.ui.dashboard.DashboardContact
import com.example.bkubuzcu.weather.ui.dashboard.DashboardPresenter
import com.example.bkubuzcu.weather.ui.dashboard.DashboardRepository
import com.example.bkubuzcu.weather.ui.dashboard.DashboardRepositoryImpl
import com.example.bkubuzcu.weather.ui.setting.SettingContact
import com.example.bkubuzcu.weather.ui.setting.SettingPresenter
import com.example.bkubuzcu.weather.ui.setting.SettingRepository
import com.example.bkubuzcu.weather.ui.setting.SettingRepositoryImpl

/**
 * Created by bkubuzcu on 06/11/18.
 * this is PresenterFactory.
 * all presenter and repository are created here.
 */
open class PresenterFactory(private val context: Context) {
    /**
     * dashboardRepository
     */
    open fun dashboardRepository(): DashboardRepository = DashboardRepositoryImpl()

    /**
     * categoryPresenter
     */
    fun categoryPresenter(): DashboardContact.Presenter = DashboardPresenter(dashboardRepository())

    open fun settingRepository():SettingRepository = SettingRepositoryImpl(context)

    fun settingPresenter(): SettingContact.Presenter = SettingPresenter(settingRepository())
}