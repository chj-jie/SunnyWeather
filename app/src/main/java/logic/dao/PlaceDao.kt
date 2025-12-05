package logic.dao

import android.content.Context
import com.example.sunnyweather.android.SunnyWeatherApplication
import com.google.gson.Gson
import logic.model.Place
import androidx.core.content.edit

object PlaceDao {
    fun savePlace(place: Place){
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }
    fun getSavedPlace(): Place{
        val placejson= sharedPreferences().getString("place","")
        return Gson().fromJson(placejson,Place::class.java)
    }

    //判断是否有数据被存储
    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences()= SunnyWeatherApplication.context.
        getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}