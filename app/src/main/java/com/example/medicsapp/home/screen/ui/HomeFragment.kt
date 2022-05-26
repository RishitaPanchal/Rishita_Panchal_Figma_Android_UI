package com.example.medicsapp.home.screen.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivityHomeScreenBinding
import com.example.medicsapp.databinding.FragmentHomeBinding
import com.example.medicsapp.see.all.doctors.SeeAllDoctorsActivity

class HomeFragment : Fragment(), View.OnClickListener {

    /** Instance variables */
    private lateinit var binding: FragmentHomeBinding
    private var topDoctorDetails: ArrayList<TopDoctorsDetails> = ArrayList()
    private var healthArticlesData: ArrayList<HealthArticlesData> = ArrayList()
    var horizontalLayout: LinearLayoutManager? = null

    /** Overridden Method */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        initTopDoctorsData()
        setUpDoctorsData()
        initArticlesData()
        setUpArticlesData()
        binding.onClick = this
        return binding.root
    }

    /** Functions */
    private fun initTopDoctorsData() {
        topDoctorDetails.add(TopDoctorsDetails(R.drawable.doctor_one, getString(R.string.dr_marcus_horizon), getString(R.string.chardiologist), getString(R.string._4_7), getString(R.string.top_doctor)))
        topDoctorDetails.add(TopDoctorsDetails(R.drawable.doctor_two, getString(R.string.dr_maria_elena), getString(R.string.psychologist),getString(R.string._4_8), getString(R.string.top_doctor)))
        topDoctorDetails.add(TopDoctorsDetails(R.drawable.doctor_three, getString(R.string.dr_stevi_jessi), getString(R.string.orthopedist),getString(R.string._4_9), getString(R.string.top_doctor)))
    }

    private fun setUpDoctorsData() {
        horizontalLayout = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewTopDoctors.layoutManager = horizontalLayout
        val itemAdapter = TopDoctorsRecyclerViewAdapter(topDoctorDetails)
        binding.recyclerViewTopDoctors.adapter = itemAdapter
    }

    private fun initArticlesData() {
        healthArticlesData.add(HealthArticlesData(R.drawable.health_article_image_one, getString(R.string.article_one_title), getString(R.string.article_one_date), getString(R.string.article_one_8_min_read)))
        healthArticlesData.add(HealthArticlesData(R.drawable.health_article_image_two, getString(R.string.article_two_title), getString(R.string.article_two_date), getString(R.string.article_one_9_min_read)))
        healthArticlesData.add(HealthArticlesData(R.drawable.health_article_image_one, getString(R.string.article_three_title), getString(R.string.article_three_date), getString(R.string.article_one_10_min_read)))
    }

    private fun setUpArticlesData() {
        horizontalLayout = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewArticles.layoutManager = horizontalLayout
        val itemAdapter = HealthArticlesAdapter(healthArticlesData)
        binding.recyclerViewArticles.adapter = itemAdapter
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.txtSeeAllDoctor.id -> startActivity(Intent(context, SeeAllDoctorsActivity::class.java))
        }
    }

}