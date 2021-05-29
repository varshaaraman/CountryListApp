package com.zohointerview.countrylist.ui.master.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zohointerview.countrylist.databinding.ItemCountryBinding
import com.zohointerview.countrylist.domain.Country

class FlagAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Country,
            FlagAdapter.CountryViewHolder>(CountryDiffUtilCallback) {

    class CountryViewHolder(private var binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.country = country
            binding.executePendingBindings()
        }
    }


    companion object CountryDiffUtilCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.countryName == newItem.countryName
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        return CountryViewHolder(ItemCountryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countrySelected = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(countrySelected)
        }
        holder.bind(countrySelected)
    }


    /**
     * Custom listener that handles clicks on [RecyclerView] items.
     */
    class OnClickListener(val clickListener: (selectedCountry: Country) -> Unit) {
        fun onClick(selectedCountry: Country) = clickListener(selectedCountry)
    }

    override fun submitList(list: List<Country>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}

