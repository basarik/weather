package com.example.bkubuzcu.weather.app

import android.app.Application
import com.example.bkubuzcu.weather.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bkubuzcu on 06/11/18.
 * this is application class.
 */
class App : Application() {
    /**
     * retrofit service
     */
    lateinit var service: ApiService
    /**
     * presenter factory
     */
    lateinit var presenterFactory: PresenterFactory
    /**
     * retrofit base url
     */
    private val baseUrl = "http://api.openweathermap.org/data/2.5/"
    /**
     * local city
     * it is kept because we have always a city
     */
    var globalCity:String = "İzmir"

    override fun onCreate() {
        super.onCreate()
        instance = this

        presenterFactory = PresenterFactory(this)

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)
    }

    companion object {
        /**
         * instance of application
         */
        lateinit var instance: App
    }
}