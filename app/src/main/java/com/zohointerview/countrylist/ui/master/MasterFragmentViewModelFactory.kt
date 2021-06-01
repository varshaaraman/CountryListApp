package com.zohointerview.countrylist.ui.master

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/****************************************************
 * View model factory class for fragment_country_master
 *
 ****************************************************/
class MasterFragmentViewModelFactory (val app: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MasterFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MasterFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("ViewModel class undefined")
        }

}