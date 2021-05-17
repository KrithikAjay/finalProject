package com.krithik.findthefreeze.ui.country


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krithik.findthefreeze.country.CountryProperty
import com.krithik.findthefreeze.databinding.CountryListBinding


class GridAdapter(private val onClickListener: OnClickListener)  : ListAdapter<CountryProperty, GridAdapter.ViewHolder>(DiffUtilCallback) {
    
        companion object DiffUtilCallback : DiffUtil.ItemCallback<CountryProperty>() {
        override fun areItemsTheSame(oldItem: CountryProperty, newItem: CountryProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CountryProperty, newItem: CountryProperty): Boolean {
            return oldItem.name == newItem.name
        }
    }
    
    class ViewHolder(private val binding : CountryListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(countryProperty: CountryProperty){
            binding.countryProperty = countryProperty
            binding.executePendingBindings()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(CountryListBinding.inflate(layoutInflater))


    }

    override fun onBindViewHolder(holder: GridAdapter.ViewHolder, position: Int) {
        val countryProperty = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(countryProperty)

        }
        holder.bind(countryProperty)

    }

    class OnClickListener(private val clickListener: (countryProperty: CountryProperty) -> Unit) {
        fun onClick(countryProperty: CountryProperty) = clickListener(countryProperty)
    }


}