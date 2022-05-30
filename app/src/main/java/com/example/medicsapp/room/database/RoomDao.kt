package com.example.medicsapp.room.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDao {

    @Insert
    suspend fun insertUser(user: Registration)

    @Query("select * from Registration")
    fun getUsers() :  LiveData<List<Registration>>

}

/** Doctors Data Accessing Objects */
@Dao
interface DoctorsDao {

    @Insert
    suspend fun insertDoctor(doctors: Doctors)

    @Delete
    suspend fun deleteDoctor(doctors: Doctors)

    @Update
    suspend fun updateDoctor(doctors: Doctors)

    @Query("select * from Doctors")
    fun getDoctors(): LiveData<List<Doctors>>

}