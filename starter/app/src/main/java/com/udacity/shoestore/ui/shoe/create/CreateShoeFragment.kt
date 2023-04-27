package com.udacity.shoestore.ui.shoe.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentCreateShoeBinding

class CreateShoeFragment : Fragment() {

    private lateinit var binding: FragmentCreateShoeBinding

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
        setupUI()
        return binding.root
    }

    private fun setupUI() {

    }
}