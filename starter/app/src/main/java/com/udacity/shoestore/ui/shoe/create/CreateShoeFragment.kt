package com.udacity.shoestore.ui.shoe.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentCreateShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.main.MainViewModel

class CreateShoeFragment : Fragment() {

    private lateinit var binding: FragmentCreateShoeBinding
    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_shoe,
            container,
            false
        )
        binding.shoe = Shoe("", 40.0, "", "")
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.btnCreate.setOnClickListener {
            createShoe()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun createShoe() {
        binding.run {
            binding.shoe?.let {
                mainViewModel.addShoe(it)
            }
            findNavController().navigateUp()
        }
    }
}