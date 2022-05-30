package com.example.medicsapp.room.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class RoomRepository(context: Context): ViewModel() {

    /** Instance variables */
    var database = DoctorsDatabase.getDatabase(context)

    /** Functions (CRUD Operations) */
    suspend fun deleteDoctor(doctors: Doctors) = database.doctorsDao().deleteDoctor(doctors)

    suspend fun insertDoctor(doctors: Doctors) =   database.doctorsDao().insertDoctor(doctors)

    suspend fun updateDoctor(doctors: Doctors) = database.doctorsDao().updateDoctor(doctors)

    fun getAllDoctors(): LiveData<List<Doctors>> = database.doctorsDao().getDoctors()
}

/** ViewModel Factory */
class VMFViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoomRepository::class.java)) {
            return RoomRepository(context) as T
        }
        throw IllegalArgumentException("ViewModel class not found!!")
    }

}