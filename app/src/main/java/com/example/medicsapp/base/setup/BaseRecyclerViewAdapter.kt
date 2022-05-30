package com.example.medicsapp.base.setup

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.medicsapp.BR

abstract class BaseRecyclerViewAdapter<T>(
    private val dataset: ArrayList<T>) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.setVariable(BR.data, item)
    }

    override fun getItemCount(): Int = dataset.size

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: Int?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(view)
}