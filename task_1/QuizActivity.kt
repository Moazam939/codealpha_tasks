package com.example.flashcardquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashcardquizapp.dataBase.DBHelper

class QuizActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
//        val id : TextView = findViewById(R.id.quizid)
        var textquestion : TextView = findViewById(R.id.textquestion)
        val next : Button = findViewById(R.id.btnnext)
        val scoring : TextView = findViewById(R.id.score)
            var edtanswer : EditText = findViewById(R.id.edtanswer)

        val database = DBHelper(this)
        val questionsget = database.select_question()
        val answerget  = database.select_answer()


//        val ids = database.select_id()
        if (questionsget.isNotEmpty()) {
            textquestion.text = questionsget[0] // Show the first question
//            id.text = ids[0]
        } else {
            textquestion.text = "No questions available."
        }

        var currentquestion = 0
        var score = 0
        next.setOnClickListener {
            val useranswer = edtanswer.text.toString()
            val correctanswer = answerget.getOrNull(currentquestion)

            if (useranswer == correctanswer){
                score++
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
            }
            currentquestion++
            if (currentquestion < questionsget.size){
                textquestion.text = questionsget[currentquestion]
                edtanswer.text.clear()

            }else{
                textquestion.text = "Quiz is Finished"
                edtanswer.text.clear()
                edtanswer.isEnabled = false
                next.isEnabled = false
                if (score > currentquestion/50){
                    Toast.makeText(this, "WellDone", Toast.LENGTH_SHORT).show()

                }else if (score == 0){
                    Toast.makeText(this, "Poor", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Average", Toast.LENGTH_SHORT).show()
                }
            }

            scoring.text = score.toString()

        }
    }
}