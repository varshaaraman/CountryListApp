package com.zohointerview.countrylist.ui.master

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zohointerview.countrylist.database.asCountry
import com.zohointerview.countrylist.database.getDatabase
import com.zohointerview.countrylist.domain.Country
import com.zohointerview.countrylist.repository.CountriesRepository
import kotlinx.coroutines.launch

/****************************************************
 * View model factory class for fragment_country_master
 *
 ****************************************************/
class MasterFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val countriesRepository = CountriesRepository(getDatabase(application))


    val database = getDatabase(application)
    val countriesList = countriesRepository.countries

    // The MutableLiveData for the filtered country
    private val _filteredCountry = MutableLiveData<List<Country>>()

    // The backing field for the filtered country
    val filteredCountry: LiveData<List<Country>>
        get() = _filteredCountry

    // The MutableLiveData for selected country
    private val _selectedCountry = MutableLiveData<Country>()

    // The backing field for the selected country
    val selectedCountry: LiveData<Country>
        get() = _selectedCountry

    //Update selected country on the item click
    fun onFlagClicked(selectedCountry: Country) {
        _selectedCountry.value = selectedCountry

    }

    /**
     * Setting the value to null post navigation
     */
    fun onNavigationComplete() {
        _selectedCountry.value = null
    }

    /**
     * Search the database to fetch a filtered list of countries
     * @param searchQuery the search string entered by the user
     */
    fun searchDatabase(searchQuery: String) {
        viewModelScope.launch {
            val filteredList = database.countryDatabaseDao.getCountryByName(searchQuery)
            _filteredCountry.value = filteredList.asCountry().distinct()
        }

    }
}