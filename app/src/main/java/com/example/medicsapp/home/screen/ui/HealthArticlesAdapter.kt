package com.example.medicsapp.home.screen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicsapp.databinding.HealthArticlesListItemBinding
import kotlin.collections.ArrayList

class HealthArticlesAdapter(private val items: ArrayList<HealthArticlesData>) :
    RecyclerView.Adapter<HealthArticlesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = HealthArticlesListItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.txtArticleTitle.text = item.articleTitle
        holder.binding.imageArticle.setImageResource(item.articleImage)
        holder.binding.txtDate.text = item.articleDate
        holder.binding.txtLastSeen.text = item.updated
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var binding: HealthArticlesListItemBinding) : RecyclerView.ViewHolder(binding.root)

}