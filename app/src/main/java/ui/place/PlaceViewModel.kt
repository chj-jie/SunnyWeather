package com.sunnyweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

import logic.Repository

import logic.model.Place

class PlaceViewModel : ViewModel() {

    //MutableLiveData是一种可变的liveData。它允许你通过setValue()或postValue()方法更新数据，并在数据变化时通知所有观察者。
    //getValue（） 获取liveData中包含的数据
    //setValue（） 给liveData设置数据，只能在主线程中调用。
    //postValue（） 用于给非主线程liveData设置数据

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = searchLiveData.switchMap{ query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query : String){
        searchLiveData.value = query
    }

    fun savePlace(place: Place)= Repository.savePlace(place)

    fun getSavedPlace()= Repository.getSavedPlace()

    fun isPlaceSaved()= Repository.isPlaceSaved()
}