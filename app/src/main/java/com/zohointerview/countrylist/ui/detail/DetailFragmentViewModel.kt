package com.zohointerview.countrylist.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zohointerview.countrylist.domain.Country
import com.zohointerview.countrylist.domain.WeatherDomain
import com.zohointerview.countrylist.domain.updateAirQuality
import com.zohointerview.countrylist.network.RetrofitProvider
import com.zohointerview.countrylist.network.asAirQuality
import com.zohointerview.countrylist.network.asWeatherDomain
import kotlinx.coroutines.launch
import timber.log.Timber

/****************************************************
 * View model class for fragment_country_detail
 *
 ****************************************************/
class DetailFragmentViewModel(
    country: Country,
    app: Application
) : AndroidViewModel(app) {

    // The MutableLiveData for the selected country
    // This is being received via safeArgs from the fragment_country_master
    private val _selectedCountry = MutableLiveData<Country>()

    // The  MutableLiveData for showing progress
    private val _shouldShowProgress = MutableLiveData<Boolean>()

    // The MutableLiveData for showing error
    private val _shouldShowError = MutableLiveData<Boolean>()

    // The MutableLiveData for selected weather
    private val _weatherSelected = MutableLiveData<WeatherDomain>()

    // The backing field for the selected country
    val selectedCountry: LiveData<Country>
        get() = _selectedCountry

    // The backing field for showing progress
    val shouldShowProgress: LiveData<Boolean>
        get() = _shouldShowProgress

    // The backing field for showing error
    val shouldShowError: LiveData<Boolean>
        get() = _shouldShowError

    // The backing field for the current weather
    val weatherDomainSelected: LiveData<WeatherDomain>
        get() = _weatherSelected

    // Initialize the _backing properties
    init {
        _selectedCountry.value = country
        _shouldShowProgress.value = false
        _shouldShowError.value = false
    }

    /**
     * Fetching weather data using retrofit by passing the capital name from country
     */
    fun getWeatherData(capital: String) {
        _shouldShowProgress.value = true
        Timber.d("Collecting weather data")
        viewModelScope.launch {

            try {
                val temp = RetrofitProvider.weatherRetrofit.getWeather(capital).asWeatherDomain()
                getAirQualityData(temp)
            } catch (e: Exception) {
                e.printStackTrace()
                _shouldShowError.value = true
            } finally {
                _shouldShowProgress.value = false
            }
        }
    }

    /**
     * Fetching Air Quality data using retrofit by passing the lat lang obtained from weather data
     */
    private fun getAirQualityData(weather: WeatherDomain) {
        viewModelScope.launch {
            _shouldShowProgress.value = false
            try {
                val airQuality = RetrofitProvider.weatherRetrofit.getAirQuality(
                    weather.latitude,
                    weather.longitude
                ).asAirQuality()
                _weatherSelected.value = weather.updateAirQuality(airQuality)

            } catch (e: Exception) {
                _shouldShowError.value = true
            } finally {
                _shouldShowProgress.value = false
            }
        }


    }

    /**
     * Setting the value to null post navigation
     * Also shouldShouldError is being cleared here
     */
    fun onNavigationComplete() {
        _weatherSelected.value = null
        _shouldShowProgress.value = false
        _shouldShowError.value = false
    }


}