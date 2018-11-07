package com.example.bkubuzcu.weather.ui.setting

import com.example.bkubuzcu.weather.app.City
import com.example.bkubuzcu.weather.base.BaseMvpPresenter
import com.example.bkubuzcu.weather.base.BaseView

/**
 * Created by bkubuzcu on 07/11/18.
 */
interface SettingContact {
    /**
     * setting view
     */
    interface View : BaseView {
        fun onLoadList(list: List<City>)
        fun onGetBatteryPercentage(percentage: Int)
    }

    /**
     * setting presenter
     */
    interface Presenter : BaseMvpPresenter<View> {
        fun loadJSONFromAssets()
        fun getBatteryPercentage()
    }

}