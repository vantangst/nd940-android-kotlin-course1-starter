package com.udacity.shoestore.ui.shoe.listing

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.ui.main.MainViewModel

class ShoeListingFragment : Fragment() {

    private lateinit var binding: FragmentShoeListingBinding
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
            R.layout.fragment_shoe_listing,
            container,
            false
        )
        setupMenu()
        setupUI()
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        mainViewModel.shoeListLiveData.observe(viewLifecycleOwner) { shoes ->
            binding.llShoes.removeAllViews()
            shoes.forEach { shoe ->
                binding.llShoes.addView(ShoeItem(requireContext()).bindData(shoe))
            }
        }
    }

    private fun setupUI() {
        binding.fabCreateShoe.setOnClickListener {
            val action = ShoeListingFragmentDirections.actionShoeListingFragmentToCreateShoeFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoe_listing_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.actionLogout -> {
                        logout()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun logout() {
        mainViewModel.logout()
        val action = ShoeListingFragmentDirections.actionShoeListingFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}