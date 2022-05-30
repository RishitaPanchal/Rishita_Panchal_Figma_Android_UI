package com.example.medicsapp.see.all.doctors

import com.example.medicsapp.R
import com.example.medicsapp.base.setup.BaseRecyclerViewAdapter
import com.example.medicsapp.home.screen.ui.TopDoctorsDetails
import com.example.medicsapp.room.database.Doctors

class SeeAllDoctorsImplementingRecyclerAdapter(items: ArrayList<TopDoctorsDetails>) : BaseRecyclerViewAdapter<TopDoctorsDetails>(items) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.doctors_list_items
    }

}