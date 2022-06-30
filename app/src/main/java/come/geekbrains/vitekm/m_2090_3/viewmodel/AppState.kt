package come.geekbrains.vitekm.m_2090_3.viewmodel

import come.geekbrains.vitekm.m_2090_3.model.PictureOfTheDayResponseDate

sealed class AppState{
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseDate) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}