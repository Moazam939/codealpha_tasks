package com.example.quizappcode

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizappcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.textview.text = "Emotions are related to the brain\n" +
//                "All feelings and emotions are related to the brain, and love is not."

        val quotes = listOf<String>(
            "Love will arise in your heart when you have no barrier between yourself and another," +
                    " when you meet and observe people without judging them.",
            "Emotions are related to the brain\n" +
                "All feelings and emotions are related to the brain, and love is not.",
            "Love can do nothing, but without it nothing can be done.",
            "Remain with sorrow completely, without any movement of thought, and you will find out of that sorrow comes passion.",
            "Tradition becomes our security, and when the mind is secure it is in decay.",
            "As long as you can get what you want, you feel happy. But if you cannot get what you want, unhappiness begins.",
            "There must be a different kind of education\n" +
                    "If there is to be any kind of social change, there must be a different kind of " +
                    "education so that children are not brought up to conform."
        )

        binding.textview.text = quotes.random()

        binding.btnget.setOnClickListener {
            val get = quotes.random()
            binding.textview.text = get
        }

        binding.share.setOnClickListener {
            var text = binding.textview.text.toString()
            val send = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(send, null))

        }
    }
}