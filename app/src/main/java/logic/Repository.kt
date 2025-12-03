package logic

import android.R.attr.query
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import logic.model.Place
import logic.network.SunnyWeatherNetwork

object Repository {
    //仓库层统一的封装入口
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}