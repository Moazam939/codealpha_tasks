package com.example.fitnesstracker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesstracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper = DBHelper(this)
        val arrlist : ArrayList<SQLiteModels> = helper.select()

        // for set goals
        val helper1 = DBHelper1(this)
        val arrlist1 : ArrayList<SQLiteModels> = helper1.select()
        binding.recycle1.layoutManager = LinearLayoutManager(this)
        val adapterss = Adapters(this, arrlist1)
        binding.recycle1.adapter = adapterss
        adapterss.notifyDataSetChanged()

        binding.txtworkout.setOnClickListener {
            val next = Intent(this, addWorkoutActivity::class.java)
            startActivity(next)
        }
        binding.txtsetgoals.setOnClickListener {
            val next = Intent(this, setGoalsActivity::class.java)
            startActivity(next)
        }


        binding.recycle.layoutManager = LinearLayoutManager(this)
        val adapters = Adapters(this, arrlist)
        binding.recycle.adapter = adapters
        adapters.notifyDataSetChanged()
    }
}