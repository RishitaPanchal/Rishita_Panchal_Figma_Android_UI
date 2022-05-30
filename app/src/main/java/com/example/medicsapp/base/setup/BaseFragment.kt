package com.example.medicsapp.base.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

open class BaseFragment<VB : ViewBinding, VM: ViewModel>(
    val bindingFactory: (LayoutInflater) -> VB,
    private val mViewModelClass: Class<VM>
) : Fragment() {

    lateinit var binding: VB
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = bindingFactory(layoutInflater)
        viewModel = ViewModelProvider(this).get(mViewModelClass)
        return binding.root
    }

}