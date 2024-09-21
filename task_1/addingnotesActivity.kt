package com.example.flashcardquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashcardquizapp.dataBase.DBHelper

class addingnotesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_addingnotes)
        val helper = DBHelper(this)
        val addingquestions : EditText = findViewById(R.id.addingquestions)
        val addinganswer : EditText = findViewById(R.id.addinganswer)
        val btnsave : Button = findViewById(R.id.btnsave)
        val add_id : TextView = findViewById(R.id.add_id)

        btnsave.setOnClickListener {
            val intent : Intent

            val questionText = addingquestions.text.toString()
            val answerText = addinganswer.text.toString()

            helper.insert(questionText, answerText)
            Toast.makeText(this,"Inserted", Toast.LENGTH_SHORT).show()
            intent = Intent(this, checkQuestionsActivity::class.java)
            intent.putExtra("addid", add_id.text.toString())
            intent.putExtra("addingquestions", questionText)
            intent.putExtra("addinganswer", answerText)

            startActivity(intent)

            finish()

            Toast.makeText(this,"Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}