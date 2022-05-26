package com.example.medicsapp.OnBoardingScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicsapp.databinding.ItemContainerViewBinding

class OnBoardingAdapter(private val items: ArrayList<OnBoardingData>) :
    RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemContainerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.textView.text = item.title
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var binding: ItemContainerViewBinding) : RecyclerView.ViewHolder(binding.root)

}