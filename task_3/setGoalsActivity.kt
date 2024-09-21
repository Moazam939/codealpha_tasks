package com.example.fitnesstracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitnesstracker.databinding.ActivitySetGoalsBinding

class setGoalsActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySetGoalsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySetGoalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setbtn.setOnClickListener {
            val database = DBHelper1(this)
            database.insert(binding.edtsetgoals.text.toString())
            Toast.makeText(this, "Set Goal", Toast.LENGTH_SHORT).show()
            binding.edtsetgoals.text = null
        }

    }
}