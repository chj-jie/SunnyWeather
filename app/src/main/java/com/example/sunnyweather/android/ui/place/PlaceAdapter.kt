package com.example.sunnyweather.android.ui.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.android.R
import com.example.sunnyweather.android.ui.weather.WeatherActivity
import com.example.sunnyweather.android.logic.model.Place


class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val placeName : TextView = view.findViewById(R.id.placeName)
        val placeAddress : TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        val holder= ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position= holder.bindingAdapterPosition
            if (position == RecyclerView.NO_POSITION || position < 0 || position >= placeList.size) {
                return@setOnClickListener
            }
            val place= placeList[position]

            //对PlaceFragment所处的Activity进行判断
            val activity = fragment.activity
            if (activity != null && activity is WeatherActivity){
                //如果是在WeatherActivity中，就关闭drawerLayout
                val drawerLayout: DrawerLayout = activity.findViewById(R.id.drawerLayout)
                drawerLayout.closeDrawers()
                activity.viewModel.locationLng = place.location.lng
                activity.viewModel.locationLat = place.location.lat
                activity.viewModel.placeName = place.name
                activity.refreshWeather()
            }else if (activity != null){
                //如果是在MainActivity中
                val intent = Intent(parent.context, WeatherActivity::class.java).apply {
                    putExtra("location_lng",place.location.lng)
                    putExtra("location_lat",place.location.lat)
                    putExtra("place_name",place.name)
                }
                fragment.startActivity(intent)
                activity.finish()
            }

            fragment.viewModel.savePlace(place)

        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }

    override fun getItemCount() = placeList.size

}