package com.example.fitnesstracker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitnesstracker.databinding.ActivityWorkoutBinding

class workoutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWorkoutBinding
    private lateinit var handler : Handler
    private lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent : Intent = getIntent()
        val name = intent.getStringExtra("tracker")

        binding.workname.text = name

        var counter = 0
        handler = Handler(Looper.getMainLooper())

        // Define the runnable
        runnable = Runnable {
            counter++
            binding.seconds.text = (counter % 60).toString()
            binding.minutes.text = (counter / 60).toString()
            handler.postDelayed(runnable, 1000) // Post again after 1 second
        }

        binding.startbtn.setOnClickListener {
            binding.textwork.text = "Time is Started"
            // Reset the counter and start the runnable
            counter = 0
            binding.seconds.text = counter.toString() // Resetting TextView
            handler.post(runnable) // Start the runnable for the first time
        }

        binding.donebtn.setOnClickListener {
            Toast.makeText(this,"Done", Toast.LENGTH_SHORT).show()
            handler.removeCallbacks(runnable)
        }

    }
}