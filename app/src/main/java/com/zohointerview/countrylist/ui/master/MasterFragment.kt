package com.zohointerview.countrylist.ui.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zohointerview.countrylist.databinding.FragmentCountryMasterBinding
import com.zohointerview.countrylist.ui.master.adapter.FlagAdapter
import timber.log.Timber

class MasterFragment : Fragment(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var viewModel: MasterFragmentViewModel
    private lateinit var flagAdapter: FlagAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        viewModel =
            ViewModelProvider(this, MasterFragmentViewModelFactory(application))
                .get(MasterFragmentViewModel::class.java)

        val binding = FragmentCountryMasterBinding.inflate(inflater)


        binding.lifecycleOwner = this
        binding.masterViewModel = viewModel
        binding.actvCountry.isSubmitButtonEnabled = true
        binding.actvCountry.setOnQueryTextListener(this)
        flagAdapter = FlagAdapter(FlagAdapter.OnClickListener {
            viewModel.onFlagClicked(it)
        })
        binding.rvCountriesList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvCountriesList.adapter = flagAdapter
        viewModel.selectedCountry.observe(this.viewLifecycleOwner, {
            it?.let {
                this.findNavController()
                    .navigate(MasterFragmentDirections.actionShowCountryDetail(it))
                viewModel.onNavigationComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onQueryTextSubmit(searchQuery: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(searchQuery: String?): Boolean {

        // If the search query is null or empty return the entire list. Hence the regex %%
        if (searchQuery == null || searchQuery.isEmpty()) {
            fetchCountriesFromDatabase("%%")
        }

        /** Its costlier to hit db for every text change and fetch results.
        hence search would triggered only if the user types atleast 4 letters
         */
        else if (searchQuery.length > 3) {
            fetchCountriesFromDatabase("%$searchQuery%")
        } else {
            //do nothing

        }

        return true
    }

    /**
     * Based on the query triggered fetch the subset of data from the database and observe the same to update
     * the result set dynamically
     */
    private fun fetchCountriesFromDatabase(searchQuery: String) {
        viewModel.searchDatabase(searchQuery)
        viewModel.filteredCountry.observe(this, { filteredList ->
            filteredList.let {
                flagAdapter.submitList(null)
                flagAdapter.submitList(it)
            }
            Timber.d(filteredList.toString())

        })
    }
}



