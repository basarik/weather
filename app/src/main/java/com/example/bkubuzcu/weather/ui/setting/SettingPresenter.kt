package com.example.bkubuzcu.weather.ui.setting

import com.example.bkubuzcu.weather.base.BasePresenter

/**
 * Created by bkubuzcu on 07/11/18.
 */
class SettingPresenter(private val repository: SettingRepository) : BasePresenter<SettingContact.View>(),
    SettingContact.Presenter {
    override fun loadJSONFromAssets() {
        view?.onLoadList(repository.loadJSONFromAssets())
    }

    override fun getBatteryPercentage() {
        view?.onGetBatteryPercentage(repository.getBatteryPercentage())
    }
}