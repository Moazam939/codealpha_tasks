package com.example.fitnesstracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitnesstracker.databinding.ActivityAddWorkoutBinding

class addWorkoutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddWorkoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.addbtn.setOnClickListener {
        val helper = DBHelper(this)
        helper.insert(binding.edtworkout.text.toString())
        Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show()
        binding.edtworkout.text = null
        }
    }
}