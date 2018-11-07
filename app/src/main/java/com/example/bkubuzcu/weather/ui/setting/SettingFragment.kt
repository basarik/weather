package com.example.bkubuzcu.weather.ui.setting

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.CompoundButton
import com.example.bkubuzcu.weather.R
import com.example.bkubuzcu.weather.app.App
import com.example.bkubuzcu.weather.app.City
import com.example.bkubuzcu.weather.app.listener.OnListItemSelectedListener
import com.example.bkubuzcu.weather.base.BaseFragment
import kotlinx.android.synthetic.main.custom_list_view.view.*
import kotlinx.android.synthetic.main.fragment_setting.*


/**
 * Created by bkubuzcu on 06/11/18.
 */
class SettingFragment : BaseFragment(), SettingContact.View, CompoundButton.OnCheckedChangeListener {
    /**
     * category presenter
     */
    private lateinit var presenter: SettingContact.Presenter

    private var arrayList = arrayListOf(
        City(35, "İzmir", "TR")
    )

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            sWifi.isChecked = !notConnected
        }
    }

    override fun onLoadList(list: List<City>) {
        arrayList = ArrayList(list)
    }

    override fun onGetBatteryPercentage(percentage: Int) {
        progressBar.progress = percentage
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        val appContext = activity?.applicationContext
        val wifiManager = appContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = p1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = App.instance.presenterFactory.settingPresenter()
        presenter.attach(this)

        //progress bar
        progressBar.max = 100

        //wifi
        sWifi.setOnCheckedChangeListener(this)

        //select city
        tvCity.text = App.instance.globalCity
        tvCity.setOnClickListener {
            var index = -1

            for (i in 0 until arrayList.size) {
                if (arrayList[i].name == App.instance.globalCity)
                    index = i
            }

            openCustomDialog(index)
        }

        presenter.loadJSONFromAssets()
        presenter.getBatteryPercentage()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()
        activity?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(broadcastReceiver)
    }

    override fun layoutResource() = R.layout.fragment_setting

    /**
     * show custom dialog and list cities
     */
    private fun openCustomDialog(checkedIndex: Int) {
        val dialog = Dialog(activity)

        val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = layoutInflater.inflate(R.layout.custom_list_view, null)

        val cityAdapter = CityAdapter(context!!, arrayList, checkedIndex, object : OnListItemSelectedListener {
            override fun onListItemSelected(item: Int) {
                dialog.dismiss()
                App.instance.globalCity = arrayList[item].name
                tvCity.text = arrayList[item].name
            }
        })

        val mLayoutManager = LinearLayoutManager(activity)
        layout.rVCustomListView.layoutManager = mLayoutManager
        layout.rVCustomListView.adapter = cityAdapter

        dialog.setContentView(layout)
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        dialog.show()
    }
}

