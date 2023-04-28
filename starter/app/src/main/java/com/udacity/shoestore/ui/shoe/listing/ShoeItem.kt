package com.udacity.shoestore.ui.shoe.listing

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe


class ShoeItem(context: Context) : ConstraintLayout(context) {

    private var binding: ShoeItemBinding

    @SuppressLint("SetTextI18n")
    fun bindData(shoe: Shoe): ShoeItem {
        binding.apply {
            ivShoe.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.shoes_logo))
            tvTitle.text = context.getString(R.string.display_name, shoe.name)
            tvDescription.text = context.getString(R.string.display_description, shoe.description)
            tvSize.text = context.getString(R.string.display_size, shoe.size.toString())
            tvCompany.text = context.getString(R.string.display_company, shoe.company)
        }
        return this
    }

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_item,
            this,
            true
        )
    }
}