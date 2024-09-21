package com.example.flashcardquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardquizapp.adapters.checkAdaptera
import com.example.flashcardquizapp.dataBase.DBHelper
import com.example.flashcardquizapp.models.Models
import com.example.flashcardquizapp.models.SQLiteModels

class checkQuestionsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_check_questions)

        val helper = DBHelper(this)
        val arrlist : ArrayList<SQLiteModels> = helper.select()

//        val get : Intent = getIntent()
//
//        val id_count = get.getStringExtra("addid")
//        val question = get.getStringExtra("addingquestions")
//        val answer = get.getStringExtra("addinganswer")




        val recycle : RecyclerView = findViewById(R.id.recycle)

        recycle.layoutManager = LinearLayoutManager(this)
        val adapters = checkAdaptera(this, list = arrlist)
        recycle.adapter = adapters

    }
}