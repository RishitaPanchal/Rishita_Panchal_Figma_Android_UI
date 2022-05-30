package com.example.medicsapp.room.database

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicsapp.databinding.ActivitySeeAllDoctorsBinding
import com.example.medicsapp.databinding.CustomDialogueBinding
import com.example.medicsapp.home.screen.ui.BottomNavigationActivity
import com.example.medicsapp.see.all.doctors.SeeAllDoctorsRecyclerAdapter
import com.example.medicsapp.sign.`in`.screen.BaseActivity
import com.example.medicsapp.sign.`in`.screen.InitStyle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SeeAllDoctorsCoreDataActivity : BaseActivity(), View.OnClickListener, SeeAllDoctorsRecyclerAdapter.ItemClicked {

    /** Instance Variable */
    private lateinit var binding: ActivitySeeAllDoctorsBinding
    private lateinit var database: DoctorsDatabase
    private lateinit var doctorsDetails: ArrayList<Doctors>
    private lateinit var factory: VMFViewModelFactory
    private lateinit var viewModel: RoomRepository
    var horizontalLayout: LinearLayoutManager? = null

    /** Overridden Method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeAllDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        factory = VMFViewModelFactory(this)
        viewModel = ViewModelProvider(this, VMFViewModelFactory(this))[RoomRepository::class.java]
        doctorsDetails = ArrayList()
        database = DoctorsDatabase.getDatabase(this)
        InitStyle.initToolBar(this, binding.toolbarTitle, binding.toolbar, "Top Doctors")
        InitStyle.goToBack(binding.toolbar, this, BottomNavigationActivity::class.java)
        getData()
        binding.onClick = this
    }

    override fun onClick(p0: View?) {}

    override fun updateClicked(doctors: Doctors) {
        updateData(doctors)
    }

    override fun deleteClicked(doctors: Doctors) {
       GlobalScope.launch { viewModel.deleteDoctor(doctors) }
    }

    /** Functions */
    private fun getData(){
        viewModel.getAllDoctors().observe(this, Observer {
            getList(it)
        })
    }

    private fun getList(data: List<Doctors>) {
        doctorsDetails = data as ArrayList<Doctors>
        setUpDoctorsData(doctorsDetails)
    }

    private fun setUpDoctorsData(data: ArrayList<Doctors>) {
        horizontalLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewTopDoctors.layoutManager = horizontalLayout
        val itemAdapter = SeeAllDoctorsRecyclerAdapter(this, data, this)
        binding.recyclerViewTopDoctors.adapter = itemAdapter
    }

    private fun updateData(doctors: Doctors) {
        val bind :CustomDialogueBinding = CustomDialogueBinding .inflate(layoutInflater)
        val alertDialogueBuilder = AlertDialog.Builder(this)
        alertDialogueBuilder.setView(bind.root)
        val dialog = alertDialogueBuilder.create()
        bind.tfDoctorName.setText(doctors.doctorName)
        bind.tfDoctorOccupation.setText(doctors.doctorOccupation)
        bind.tfDoctorRating.setText(doctors.star)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        bind.btnLogin.setOnClickListener {
            doctors.doctorName = bind.tfDoctorName.text.toString()
            doctors.doctorOccupation = bind.tfDoctorOccupation.text.toString()
            doctors.star = bind.tfDoctorRating.text.toString()
            GlobalScope.launch { viewModel.updateDoctor(doctors) }
            dialog.dismiss()
        }
    }

}