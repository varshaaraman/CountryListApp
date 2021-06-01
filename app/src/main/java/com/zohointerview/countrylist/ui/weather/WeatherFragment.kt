package com.zohointerview.countrylist.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zohointerview.countrylist.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application
        val binding = FragmentWeatherBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val displayedWeather = WeatherFragmentArgs.fromBundle(requireArguments()).selectedWeather
        val viewModelFactory = WeatherFragmentViewModelFactory(displayedWeather, application)
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(WeatherFragmentViewModel::class.java)
        binding.weatherViewModel = viewModel
        return binding.root
    }
}