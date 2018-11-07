package com.example.bkubuzcu.weather.ui.setting

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bkubuzcu.weather.R
import com.example.bkubuzcu.weather.app.City
import com.example.bkubuzcu.weather.app.listener.OnListItemSelectedListener
import kotlinx.android.synthetic.main.custom_list_view_item.view.*

/**
 * Created by bkubuzcu on 06/11/18.
 */
class CityAdapter(
    private val context: Context,
    private var items: ArrayList<City>,
    private var checkedIndex: Int = -1,
    private val listener: OnListItemSelectedListener
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.custom_list_view_item, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = item.name

        holder.index = position

        holder.cb.isChecked = checkedIndex == position
        holder.tvName?.setOnClickListener {
            click(position)
        }

        holder.cb.setOnClickListener{
            click(position)
        }
    }

    private fun click(position: Int){
        listener.onListItemSelected(position)
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cb = itemView.cb!!
    var tvName: TextView = itemView.tvName
    var index: Int = 0
}