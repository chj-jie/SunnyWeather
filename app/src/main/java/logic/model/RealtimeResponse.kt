package logic.model

import com.google.gson.annotations.SerializedName

//status：请求状态（如 "ok" 表示成功）
//result：包含实际数据的 Result 对象
data class RealtimeResponse(val status : String,val result : Result){
    //realtime：实时天气数据的容器
    data class Result(val realtime : Realtime)
    //skycon：天气状况（如云量、降水类型）
//temperature：当前温度
//airQuality：空气质量数据（通过 @SerializedName 映射 JSON 中的 air_quality 字段）
    data class Realtime(val skycon : String,val temperature : Float,@SerializedName("air_quality") val airQuality : AirQuality)
    //aqi：空气质量指数（AQI）
    data class AirQuality(val aqi : AQI)
    //chn：中国标准的 AQI 数值
    data class AQI(val chn : Float)

}