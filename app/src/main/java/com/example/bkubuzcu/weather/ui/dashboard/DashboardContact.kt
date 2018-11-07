package com.example.bkubuzcu.weather.ui.dashboard

import com.example.bkubuzcu.weather.app.WeatherResponse
import com.example.bkubuzcu.weather.base.BaseMvpPresenter
import com.example.bkubuzcu.weather.base.BaseView

/**
 * Created by bkubuzcu on 06/11/18.
 * this is DashboardContact.
 * this hosts all interface about view and presenter interactions
 */
interface DashboardContact{
    /**
     * weather view
     */
    interface View : BaseView {
        fun onGetWeather(weather: WeatherResponse)
    }

    /**
     * weather presenter
     */
    interface Presenter : BaseMvpPresenter<View> {
        fun getWeather()
    }
}