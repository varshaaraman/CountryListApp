package com.zohointerview.countrylist.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zohointerview.countrylist.databinding.FragmentCountryDetailBinding
import timber.log.Timber

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(activity).application
        val binding = FragmentCountryDetailBinding.inflate(inflater)
        val selectedCountry = DetailFragmentArgs.fromBundle(requireArguments()).selectedCountry
        val viewModelFactory = DetailFragmentViewModelFactory(selectedCountry, application)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(DetailFragmentViewModel::class.java)
        binding.detailViewModel = viewModel
        viewModel.weatherDomainSelected.observe(this.viewLifecycleOwner, {
            Timber.d("Awaiting navigation")
            it?.let {
                Timber.d("Navigation should start")
                this.findNavController().navigate(DetailFragmentDirections.actionShowWeather(it))
                viewModel.onNavigationComplete()
            }
        })

        binding.lifecycleOwner = this
        viewModel.shouldShowProgress.observe(this.viewLifecycleOwner, {
            onProgressChanged(binding, it)

        })
        viewModel.shouldShowError.observe(this.viewLifecycleOwner, {
            onError(binding, it)

        })
        binding.btnCntryDtlWthr.setOnClickListener { view: View ->
            viewModel.getWeatherData(selectedCountry.capital)
        }
        return binding.root
    }

    /**
     * Displaying progress bar
     * @param binding data binding reference
     * @param shouldShowProgress boolean to decide whether progress should be shown or not
     */
    private fun onProgressChanged(
        binding: FragmentCountryDetailBinding,
        shouldShowProgress: Boolean
    ) {
        binding.flProgressHolder.visibility = if (shouldShowProgress) View.VISIBLE else View.GONE
        binding.cntryDtlCntent.visibility = if (shouldShowProgress) View.GONE else View.VISIBLE
    }

    /**
     * Displaying error banner
     * @param binding data binding reference
     * @param shouldShowError boolean to decide whether error banner should be shown or not
     */
    private fun onError(binding: FragmentCountryDetailBinding, shouldShowError: Boolean) {
        binding.errorView.visibility = if (shouldShowError) View.VISIBLE else View.GONE

    }
}