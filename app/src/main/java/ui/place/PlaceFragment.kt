package com.sunnyweather.android.ui.place


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.android.R
import com.sunnyweather.android.ui.weather.WeatherActivity


class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (viewModel.isPlaceSaved()){
            val place =viewModel.getSavedPlace()
            val intent= Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng",place.location.lng)
                putExtra("location_lat",place.location.lat)
                putExtra("place_name",place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

        val  recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val searchPlaceEdit = view?.findViewById<EditText>(R.id.searchPlaceEdit)
        val bgImageView = view?.findViewById<ImageView>(R.id.bgImageView)

        val layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager
        adapter = PlaceAdapter(this,viewModel.placeList)
        recyclerView?.adapter = adapter

        searchPlaceEdit?.doAfterTextChanged { editable ->
            val content = editable?.toString().orEmpty()
            if (content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                recyclerView?.visibility = View.GONE
                bgImageView?.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }


        viewModel.placeLiveData.observe(viewLifecycleOwner){ result ->
            val place = result.getOrNull()
            if (place != null){
                //显示
                recyclerView?.visibility = View.VISIBLE
                //隐藏
                bgImageView?.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(place)
                //用于通知适配器其内部数据源已经发生变化，需要重新计算并刷新整个列表的显示
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }

    }

}
