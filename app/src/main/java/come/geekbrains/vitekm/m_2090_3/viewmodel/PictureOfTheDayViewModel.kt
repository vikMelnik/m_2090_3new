package come.geekbrains.vitekm.m_2090_3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import come.geekbrains.vitekm.m_2090_3.BuildConfig
import come.geekbrains.vitekm.m_2090_3.model.PictureOfTheDayResponseDate
import come.geekbrains.vitekm.m_2090_3.model.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData(),
                               private val repositoryImpl: RepositoryImpl = RepositoryImpl()) :
    ViewModel() {
    fun getLiveData():MutableLiveData<AppState>{
        //
        return liveData
    }

    fun sendRequest(date: String) {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDayByDate(BuildConfig.NASA_API_KEY, date)
            .enqueue(callback)
//        repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
//            .enqueue(callback)
    }


    private val callback = object : Callback<PictureOfTheDayResponseDate> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseDate>,
            response: Response<PictureOfTheDayResponseDate>
        ) {
            if(response.isSuccessful){
                liveData.postValue(AppState.Success(response.body()!!))
            }else{
                liveData.postValue(AppState.Error(throw IllegalStateException("что-то пошло не так")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseDate>, t: Throwable) {
            // TODO HW
            liveData.postValue(AppState.Error(throw RuntimeException(" сетевой сбой сервера ")))
            t.printStackTrace()
        }
    }
}