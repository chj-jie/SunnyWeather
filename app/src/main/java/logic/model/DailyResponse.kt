package logic.model

import com.google.gson.annotations.SerializedName
import java.util.Date

//status：请求状态（如 "ok" 表示成功）
//result：包含实际数据的 Result 对象
data class DailyResponse(val status : String,val result : Result){

    data class Result(val daily : Daily)
    //daily：每日预报数据的容器
    data class Daily(val temperature : List<Temperature> , val skycon : List<Skycon>,
                     @SerializedName("life_index") val lifeIndex : LifeIndex)
    //temperature：温度预报列表（每天一条记录）
//skycon：天气状况（如云量、降水类型）
//lifeIndex：生活指数（通过 @SerializedName 映射 JSON 中的 life_index 字段）
    data class Temperature(val max : Float, val min : Float)
    //max：最高温度
//min：最低温度
    data class Skycon(val value : String, val date : Date)
    //value：天气状况描述（如 "CLEAR_DAY"、"RAIN"）
//date：对应的日期
    data class LifeIndex(val coldRisk : List<LifeDescription>, val carWashing : List<LifeDescription>,
                         val ultraviolet : List<LifeDescription>, val dressing : List<LifeDescription>)
    //coldRisk：感冒风险
//carWashing：洗车指数
//ultraviolet：紫外线指数
//dressing：穿衣指数
    data class LifeDescription(val desc : String)
//desc：具体的文字描述（如 "较适宜洗车"、"紫外线强度弱"）
}