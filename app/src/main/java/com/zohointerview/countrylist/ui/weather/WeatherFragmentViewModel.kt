package com.zohointerview.countrylist.ui.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zohointerview.countrylist.domain.WeatherDomain

/****************************************************
 * View model class for weather
 *
 ****************************************************/
class WeatherFragmentViewModel(
    weatherDomain: WeatherDomain,
    app: Application
) : AndroidViewModel(app) {

    // The MutableLiveData for the selected country
    // This is being received via safeArgs from the fragment_country_detail
    private val _displayedWeather = MutableLiveData<WeatherDomain>()

    // The backing field for the selected weather
    val displayedWeatherDomain: LiveData<WeatherDomain>
        get() = _displayedWeather

    // Initialize the _backing properties
    init {
        _displayedWeather.value = weatherDomain
    }
}
