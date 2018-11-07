package com.example.bkubuzcu.weather.ui.dashboard

import android.os.Bundle
import android.view.View
import com.example.bkubuzcu.weather.R
import com.example.bkubuzcu.weather.app.App
import com.example.bkubuzcu.weather.app.WeatherResponse
import com.example.bkubuzcu.weather.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * Created by bkubuzcu on 06/11/18.
 */
class DashboardFragment : BaseFragment(), DashboardContact.View {

    /**
     * category presenter
     */
    private lateinit var presenter: DashboardContact.Presenter

    override fun onGetWeather(weather: WeatherResponse) {
        //city
        val city = weather.name + " / " + weather.weather[0].description
        tvCity.text = city

        //min and max temperature
        tvTemperatureMin.text = weather.main.temp_min
        tvTemperatureMax.text = weather.main.temp_max

        val temp = weather.main.temp_min + " / " + weather.main.temp_max
        tvTemperature.text = temp

        //get icon
        getImage(weather.weather[0].icon)
    }

    override fun layoutResource() = R.layout.fragment_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = App.instance.presenterFactory.categoryPresenter()
        presenter.attach(this)
        refresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    /**
     * get weather again.
     */
    fun refresh() {
        presenter.getWeather()
    }

    /**
     * get image via Picasso
     */
    private fun getImage(iconId: String) {
        val path = "http://openweathermap.org/img/w/$iconId.png"
        Picasso.get().load(path).into(imageView)
    }
}
