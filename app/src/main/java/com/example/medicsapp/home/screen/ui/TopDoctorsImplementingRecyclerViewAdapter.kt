package com.example.medicsapp.home.screen.ui

import com.example.medicsapp.R
import com.example.medicsapp.base.setup.BaseRecyclerViewAdapter

class TopDoctorsImplementingRecyclerViewAdapter(items: ArrayList<TopDoctorsDetails>) : BaseRecyclerViewAdapter<TopDoctorsDetails>(items) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.top_doctors_list_item
    }

}