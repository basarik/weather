package com.example.bkubuzcu.weather.ui.setting

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.example.bkubuzcu.weather.app.City
import com.example.bkubuzcu.weather.app.JsonCity
import com.google.gson.Gson
import java.io.IOException

/**
 * Created by bkubuzcu on 07/11/18.
 */
class SettingRepositoryImpl(val context: Context) : SettingRepository {
    override fun loadJSONFromAssets(): List<City> {
        var json: String?
        try {
            val inputStream = context.assets.open("cities.json")

            val size = inputStream.available()

            val buffer = ByteArray(size)

            inputStream.read(buffer)

            inputStream.close()

            json = String(buffer, Charsets.UTF_8)

        } catch (ex: IOException) {
            ex.printStackTrace()
            json = null
        }

        //jSon and update cities
        val gSon = Gson()

        return if (json != null){
            gSon.fromJson(json, JsonCity::class.java).results
        } else{
            arrayListOf()
        }
    }

    override fun getBatteryPercentage(): Int {
        val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = context.registerReceiver(null, iFilter)

        val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1

        val batteryPct = level / scale.toFloat()

        return (batteryPct * 100).toInt()
    }
}

interface SettingRepository {
    /**
     * load json from assetts
     */
    fun loadJSONFromAssets(): List<City>

    /**
     * get battery percentage
     */
    fun getBatteryPercentage(): Int
}