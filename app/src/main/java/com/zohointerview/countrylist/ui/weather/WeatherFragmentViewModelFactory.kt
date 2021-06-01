package com.zohointerview.countrylist.ui.weather

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zohointerview.countrylist.domain.WeatherDomain

/****************************************************
 * View model factory class for fragment_weather
 *
 ****************************************************/
class WeatherFragmentViewModelFactory(
    private val weatherDomain: WeatherDomain,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherFragmentViewModel::class.java)) {
            return WeatherFragmentViewModel(weatherDomain, application) as T
        }
        throw IllegalArgumentException("ViewModel class undefined")
    }
}