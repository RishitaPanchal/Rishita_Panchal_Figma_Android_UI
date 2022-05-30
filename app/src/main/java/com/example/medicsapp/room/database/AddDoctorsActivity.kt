package com.example.medicsapp.room.database

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivityAddDoctorsBinding
import com.example.medicsapp.sign.`in`.screen.BaseActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class AddDoctorsActivity : BaseActivity(), View.OnClickListener {

    /** Instance variables */
    private lateinit var binding: ActivityAddDoctorsBinding
    private lateinit var factory: VMFViewModelFactory
    private lateinit var viewModel: RoomRepository

    /** Overridden Methods */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        factory = VMFViewModelFactory(this)
        viewModel = ViewModelProvider(this, VMFViewModelFactory(this))[RoomRepository::class.java]
        binding.onClick = this
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnAddDoctor.id -> insertData()
            binding.btnLogin.id -> {
                startActivity(Intent(this, SeeAllDoctorsCoreDataActivity::class.java))
            }
        }
    }

    /** Function to insert Data */
    private fun insertData() {
        val doctor = Doctors(
            0,
            R.drawable.doctor_list_img_one,
            binding.tfDoctorName.text.toString(),
            binding.tfDoctorOccupation.text.toString(),
            "4.5",
            binding.tfDoctorDistance.text.toString(),
            Date()
        )
        if (binding.tfDoctorName.text?.isEmpty() == true || binding.tfDoctorOccupation.text?.isEmpty() == true || binding.tfDoctorDistance.text?.isEmpty() == true)
            updateData() else GlobalScope.launch { viewModel.insertDoctor(doctor).apply { runOnUiThread {  updateData() } } }
    }

    private fun updateData() {
       AlertExtension.showDialogue(this, "SUCCESS", "Yayy!! You have successfully added in a list") {
           if(it) startActivity(Intent(this, SeeAllDoctorsCoreDataActivity::class.java))
       }
    }

}