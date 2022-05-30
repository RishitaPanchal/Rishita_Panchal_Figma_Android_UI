package com.example.medicsapp.see.all.doctors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.medicsapp.R
import com.example.medicsapp.databinding.DoctorsCoreListItemsBinding
import com.example.medicsapp.room.database.Doctors

class SeeAllDoctorsRecyclerAdapter(
    val context: Context,
    private val items: ArrayList<Doctors>,
    itemClick: ItemClicked
) :
    RecyclerView.Adapter<SeeAllDoctorsRecyclerAdapter.ViewHolder>() {

    var itemClicked: ItemClicked = itemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DoctorsCoreListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.txtDoctorName.text = item.doctorName
        holder.binding.imgDoctor.setImageResource(item.doctorAvatar)
        holder.binding.txtDoctorOccupation.text = item.doctorOccupation
        holder.binding.txtDistance.text = item.distance
        holder.binding.txtStar.text = item.star
        holder.binding.imgMenu.setOnClickListener {
            showPopMenu(it, item)
        }
    }

    private fun showPopMenu(view: View, doctors: Doctors) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.recyclerview_item_menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.update -> itemClicked.updateClicked(doctors)
                R.id.delete -> itemClicked.deleteClicked(doctors)
            }
            true
        }
        popupMenu.show()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(var binding: DoctorsCoreListItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ItemClicked {
        fun updateClicked(doctors: Doctors)
        fun deleteClicked(doctors: Doctors)
    }

}