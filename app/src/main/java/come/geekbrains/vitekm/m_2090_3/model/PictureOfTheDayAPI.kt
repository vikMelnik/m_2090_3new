package come.geekbrains.vitekm.m_2090_3.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey:String):Call<PictureOfTheDayResponseDate>

    @GET("planetary/apod") // TODO HW **
    fun getPictureOfTheDayByDate(@Query("api_key") apiKey:String,@Query("date") date:String):Call<PictureOfTheDayResponseDate>
}