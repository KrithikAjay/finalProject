package com.krithik.findthefreeze.ui.country

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.krithik.findthefreeze.country.CountryApi

import com.krithik.findthefreeze.database.CountryDatabase
import com.krithik.findthefreeze.database.CountryRepository
import com.krithik.findthefreeze.databinding.CountryFragmentBinding

class CountryFragment : Fragment() {

    companion object {
        fun newInstance() = CountryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = CountryFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getDatabase(application).countryDatabaseDao()
        val repository  = CountryRepository(dataSource,CountryApi)

        val viewModelFactory = CountryViewModelFactory(repository)

        val viewModel  = ViewModelProvider(this,viewModelFactory).get(CountryViewModel::class.java)


        binding.countryViewModel = viewModel
        binding.recyclerView.layoutManager =  StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

       val adapter = GridAdapter(GridAdapter.OnClickListener{
           viewModel.displaySelectedProperty(it)

       })
        binding.recyclerView.adapter = adapter

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(CountryFragmentDirections.actionCountryFragmentToCountryDetailFragment(it))
                viewModel.displaySelectedPropertyComplete()

            }
        })

        return (binding.root)

    }



}