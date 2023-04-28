package com.udacity.shoestore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    // store data temporary
    companion object {
        private var isLogin = false
        private val shoesCache: MutableList<Shoe> = mutableListOf()
    }

    val loginState: Boolean
        get() = isLogin

    private val _shoeListLiveData = MutableLiveData<MutableList<Shoe>>()
    val shoeListLiveData: LiveData<MutableList<Shoe>>
        get() = _shoeListLiveData

    private val _showActionBarState = MutableLiveData<Boolean>()
    val showActionBarState: LiveData<Boolean>
        get() = _showActionBarState

    init {
        _showActionBarState.postValue(true)
        initShoeCache()
    }

    private fun initShoeCache() {
        val shoes = shoesCache
        if (shoes.isEmpty()) {
            val shoe = Shoe(
                "Leather Safety Shoes with Steel Toe",
                45.0,
                "VietNam",
                "Leather Safety Shoes Steel Toe Foot Protection Industrial Safety Workers' Shoes Jogger Breathable Shoes"
            )
            shoes.add(shoe)
        }
        _shoeListLiveData.postValue(shoes)
    }

    fun login() {
        isLogin = true
    }

    fun logout() {
        shoesCache.clear()
        initShoeCache()
        isLogin = false
    }

    fun addShoe(shoe: Shoe) {
        shoesCache.add(shoe)
        _shoeListLiveData.postValue(shoesCache)
    }

    fun displayActionbar(display: Boolean) {
        _showActionBarState.postValue(display)
    }
}