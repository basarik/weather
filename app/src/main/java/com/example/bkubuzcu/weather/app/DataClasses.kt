package com.example.bkubuzcu.weather.app

import java.io.Serializable

/**
 * Created by bkubuzcu on 06/11/18.
 * this is DataClasses.
 * all models are created here as data class.
 */

/**
 * city model
 */
data class City(
    val id: Int,
    val name: String,
    val country: String
)

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Temperature,
    val name: String
) : Serializable

data class Weather(
    val main: String,
    val description: String,
    val icon: String
) : Serializable

data class Temperature(
    val temp_min: String,
    val temp_max: String
) : Serializable

data class JsonCity(
    val results: List<City>
) : Serializable