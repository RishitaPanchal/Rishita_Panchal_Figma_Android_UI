package com.example.medicsapp.see.all.doctors

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivitySeeAllDoctorsBinding
import com.example.medicsapp.home.screen.ui.BottomNavigationActivity
import com.example.medicsapp.home.screen.ui.TopDoctorsDetails
import com.example.medicsapp.sign.`in`.screen.BaseActivity
import com.example.medicsapp.sign.`in`.screen.InitStyle

class SeeAllDoctorsActivity : BaseActivity() {

    /** Instance Variable */
    private lateinit var binding: ActivitySeeAllDoctorsBinding
    private var topDoctorDetails: ArrayList<TopDoctorsDetails> = ArrayList()
    var horizontalLayout: LinearLayoutManager? = null

    /** Overridden Method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeAllDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitStyle.initToolBar(this, binding.toolbarTitle, binding.toolbar, "Top Doctors")
        InitStyle.goToBack(binding.toolbar, this, BottomNavigationActivity::class.java)
        initTopDoctorsData()
        setUpDoctorsData()
    }

    /** Functions */
    private fun initTopDoctorsData() {
        topDoctorDetails.add(
            TopDoctorsDetails(
                R.drawable.doctor_list_img_one,
                getString(R.string.dr_marcus_horizon),
                getString(R.string.chardiologist),
                getString(R.string._4_7),
                getString(R.string.top_doctor)
            )
        )
        topDoctorDetails.add(
            TopDoctorsDetails(
                R.drawable.doctor_list_img_two,
                getString(R.string.dr_maria_elena),
                getString(R.string.psychologist),
                getString(R.string._4_8),
                getString(R.string.top_doctor)
            )
        )
        topDoctorDetails.add(
            TopDoctorsDetails(
                R.drawable.doctor_list_img_three,
                getString(R.string.dr_stevi_jessi),
                getString(R.string.orthopedist),
                getString(R.string._4_9),
                getString(R.string.top_doctor)
            )
        )
        topDoctorDetails.add(
            TopDoctorsDetails(
                R.drawable.doctor_list_img_one,
                getString(R.string.dr_marcus_horizon),
                getString(R.string.chardiologist),
                getString(R.string._4_7),
                getString(R.string.top_doctor)
            )
        )
        topDoctorDetails.add(
            TopDoctorsDetails(
                R.drawable.doctor_list_img_two,
                getString(R.string.dr_maria_elena),
                getString(R.string.psychologist),
                getString(R.string._4_8),
                getString(R.string.top_doctor)
            )
        )
        topDoctorDetails.add(
            TopDoctorsDetails(
                R.drawable.doctor_list_img_three,
                getString(R.string.dr_stevi_jessi),
                getString(R.string.orthopedist),
                getString(R.string._4_9),
                getString(R.string.top_doctor)
            )
        )
    }

    private fun setUpDoctorsData() {
        horizontalLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewTopDoctors.layoutManager = horizontalLayout
        val itemAdapter = SeeAllDoctorsImplementingRecyclerAdapter(topDoctorDetails)
        binding.recyclerViewTopDoctors.adapter = itemAdapter
    }

}
