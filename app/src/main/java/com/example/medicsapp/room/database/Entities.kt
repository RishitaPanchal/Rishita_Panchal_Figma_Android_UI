package com.example.medicsapp.room.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/** Registration Entity */
@Entity(tableName = "Registration")
data class Registration(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val name: String,
    val password: String
)

/** Doctor's Entity */
@Entity(tableName = "Doctors")
data class Doctors(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val doctorAvatar: Int,
    var doctorName: String,
    var doctorOccupation: String,
    var star: String,
    val distance: String,
    var createdAt: Date
)