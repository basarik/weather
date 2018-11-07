package com.example.bkubuzcu.weather.ui.dashboard

import com.example.bkubuzcu.weather.app.WeatherResponse
import com.example.bkubuzcu.weather.base.BasePresenter
import com.example.bkubuzcu.weather.network.ApiResponse
import com.example.bkubuzcu.weather.network.OnResponseListener

/**
 * Created by bkubuzcu on 06/11/18.
 */
class DashboardPresenter(private val repository: DashboardRepository) : BasePresenter<DashboardContact.View>(),
    DashboardContact.Presenter,
    OnResponseListener<WeatherResponse> {
    override fun getWeather() {
        view?.showProgress()
        repository.getWeather(this)
    }

    override fun onResponse(data: ApiResponse<WeatherResponse>) {
        view?.hideProgress()
        data.error?.apply {
            view?.onError(this)
        }

        data.success?.apply {
            view?.onGetWeather(this)
        }
    }
}