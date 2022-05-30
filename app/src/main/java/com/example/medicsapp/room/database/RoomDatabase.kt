package com.example.medicsapp.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/** Registration Database */
@Database(entities = [Registration::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun registrationDao(): RoomDao
}

/** Doctors Database */
@Database(entities = [Doctors::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class DoctorsDatabase : RoomDatabase() {

    /** Singleton object */
    companion object {
        private var shared: DoctorsDatabase? = null
        fun getDatabase(context: Context): DoctorsDatabase {
            if (shared == null) shared =
                Room.databaseBuilder(context, DoctorsDatabase::class.java, "DoctorsDataBase")
                    .build()
            return shared as DoctorsDatabase
        }
    }

    abstract fun doctorsDao(): DoctorsDao

}