package com.example.bkubuzcu.weather.ui.dashboard

import com.example.bkubuzcu.weather.app.App
import com.example.bkubuzcu.weather.app.Constants
import com.example.bkubuzcu.weather.app.WeatherResponse
import com.example.bkubuzcu.weather.network.ApiResponse
import com.example.bkubuzcu.weather.network.OnResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by bkubuzcu on 06/11/18.
 */
class DashboardRepositoryImpl : DashboardRepository {
    override fun getWeather(listener: OnResponseListener<WeatherResponse>) {
        App.instance.service.getWeather(App.instance.globalCity).enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                listener.onResponse(ApiResponse.error(t))
            }

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.body() != null && response.code() == Constants.RESPONSE_SUCCESS_CODE) {
                    listener.onResponse(ApiResponse.success(response.body()!!))
                } else {
                    listener.onResponse(ApiResponse.error(Throwable(Constants.ERROR_OCCURRED)))
                }
            }
        })
    }
}

interface DashboardRepository {
    /**
     * call weather service
     */
    fun getWeather(listener: OnResponseListener<WeatherResponse>)
}