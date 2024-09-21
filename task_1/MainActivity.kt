package com.example.flashcardquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        val add : Button = findViewById(R.id.add)
        val start : Button = findViewById(R.id.start)
        val questions : Button = findViewById(R.id.question)

        toolbar.setTitle("Quiz App")
        toolbar.setSubtitle("FlashCard")

        add.setOnClickListener {
            val intent = Intent(this, addingnotesActivity::class.java)
            startActivity(intent)
        }
        start.setOnClickListener {
            val next = Intent(this, QuizActivity::class.java)
            startActivity(next)
        }
        questions.setOnClickListener {
            val inext = Intent(this, checkQuestionsActivity::class.java)
            startActivity(inext)
        }
    }
}