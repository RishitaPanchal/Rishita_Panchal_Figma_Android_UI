package com.example.medicsapp.see.all.doctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicsapp.databinding.DoctorsListItemsBinding
import com.example.medicsapp.home.screen.ui.TopDoctorsDetails

class SeeAllDoctorsRecyclerAdapter(private val items: ArrayList<TopDoctorsDetails>) :
    RecyclerView.Adapter<SeeAllDoctorsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DoctorsListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.txtDoctorName.text = item.doctorName
        holder.binding.imgDoctor.setImageResource(item.doctorAvatar)
        holder.binding.txtDoctorOccupation.text = item.doctorOccupation
        holder.binding.txtDistance.text = item.distance
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var binding: DoctorsListItemsBinding) : RecyclerView.ViewHolder(binding.root)

}