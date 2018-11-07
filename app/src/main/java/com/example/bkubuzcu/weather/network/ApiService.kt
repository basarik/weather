package com.example.bkubuzcu.weather.network

import com.example.bkubuzcu.weather.app.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bkubuzcu on 06/11/18.
 * this is created to get items from api
 */
interface ApiService {
    /**
     * get city of Turkey
     */
    @GET("weather?appid=fe5464f277461029b6c0b84c8b8d193d&units=metric")
    fun getWeather(@Query("q") query: String): Call<WeatherResponse>
}