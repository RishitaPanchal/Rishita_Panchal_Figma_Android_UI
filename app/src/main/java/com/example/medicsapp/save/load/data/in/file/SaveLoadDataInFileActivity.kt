package com.example.medicsapp.save.load.data.`in`.file

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivitySaveLoadDataInFileBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class SaveLoadDataInFileActivity : AppCompatActivity(), View.OnClickListener {

    /** Instance variable */
    private lateinit var binding: ActivitySaveLoadDataInFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveLoadDataInFileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
    }

    private fun saveIntoFile() {
        try {
            val fileOutputStream = openFileOutput("Example.txt", Context.MODE_PRIVATE)
            val outputWriter = OutputStreamWriter(fileOutputStream)
            outputWriter.write(binding.tfName.text.toString())
            outputWriter.close()
            Toast.makeText(this, "Data saved successfully.....", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadFromFile() {
        try {
            val fileInputStream = openFileInput("Example.txt")
            val inputReader = InputStreamReader(fileInputStream)
            val output = inputReader.readText()
            binding.tfSurname.setText(output)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            binding.btnSave.id -> saveIntoFile()
            binding.btnLoad.id -> loadFromFile()
        }
    }

}