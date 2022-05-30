package com.example.medicsapp.base.setup

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.medicsapp.R
import com.example.medicsapp.room.database.VMFViewModelFactory

abstract class BaseAppActivity<V : ViewBinding, VM : ViewModel>(
    val bindingFactory: (LayoutInflater) -> V,
    private val mViewModelClass: Class<VM>
) : AppCompatActivity() {

    /** Instance variables */
    lateinit var binding: V
    lateinit var viewModel: VM

    /** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(mViewModelClass as Class<VM>)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        statusBarColor()
    }

    /** Functions */
    private fun statusBarColor() {
        window.statusBarColor =
            Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(this, R.color.white)))
    }

}