package com.udacity.shoestore.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentSplashBinding
import com.udacity.shoestore.ui.main.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.displayActionbar(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_splash,
            container,
            false
        )
        lifecycleScope.launch {
            // delay to show splash screen
            delay(1000)
            launchFirstScreen()
            viewModel.displayActionbar(true)
        }
        return binding.root
    }

    override fun onDestroy() {
        viewModel.displayActionbar(true)
        super.onDestroy()
    }

    private fun launchFirstScreen() {
        if (viewModel.loginState) {
            gotoHome()
        } else {
            gotoLogin()
        }
    }

    private fun gotoHome() {
        val navOptions: NavOptions = NavOptions.Builder()
            .setPopUpTo(R.id.splashFragment, true)
            .build()
        val action = SplashFragmentDirections.actionSplashFragmentToShoeListingFragment()
        findNavController().navigate(action, navOptions = navOptions)
    }

    private fun gotoLogin() {
        val navOptions: NavOptions = NavOptions.Builder()
            .setPopUpTo(R.id.splashFragment, true)
            .build()
        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        findNavController().navigate(action, navOptions = navOptions)
    }
}