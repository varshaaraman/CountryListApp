package com.zohointerview.countrylist.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zohointerview.countrylist.domain.Country

/****************************************************
 * View model factory class for fragment_country_detail
 *
 ****************************************************/
class DetailFragmentViewModelFactory(
    private val country: Country,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)) {
            return DetailFragmentViewModel(country, application) as T
        }
        throw IllegalArgumentException("ViewModel class undefined")
    }
}