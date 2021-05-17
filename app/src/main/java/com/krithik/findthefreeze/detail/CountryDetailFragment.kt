package com.krithik.findthefreeze.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.krithik.findthefreeze.R
import com.krithik.findthefreeze.databinding.CountryDetailFragmentBinding

class CountryDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CountryDetailFragment()
    }

    private lateinit var viewModel: CountryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CountryDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val selectedProperty = CountryDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = CountryDetailViewModelFactory(selectedProperty)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CountryDetailViewModel::class.java)


        binding.detailViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}