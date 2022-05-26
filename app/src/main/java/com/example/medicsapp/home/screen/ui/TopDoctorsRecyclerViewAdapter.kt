package com.example.medicsapp.home.screen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicsapp.OnBoardingScreen.OnBoardingData
import com.example.medicsapp.R
import com.example.medicsapp.databinding.TopDoctorsListItemBinding

class TopDoctorsRecyclerViewAdapter(private val items: ArrayList<TopDoctorsDetails>) :
    RecyclerView.Adapter<TopDoctorsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TopDoctorsListItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.txtDoctorName.text = item.doctorName
        holder.binding.txtDoctorOccupation.text =item.doctorOccupation
        holder.binding.txtStar.text = item.star
        holder.binding.imgDoctor.setImageResource(item.doctorAvatar)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var binding: TopDoctorsListItemBinding) : RecyclerView.ViewHolder(binding.root) { }

}