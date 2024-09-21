package com.example.flashcardquizapp.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.flashcardquizapp.models.SQLiteModels

val DATABASE_NAME : String = "Data.db"
val TABLE_NAME : String = "Quiz"
val VERSION : Int = 1
val QUESTION : String = "Question"
val ANSWER : String = "Answer"
val SCORE : String = "Score"
val ID : String = "id"
open class DBHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = " Create Table " + TABLE_NAME + "(" + ID + " Integer Primary Key autoincrement ," +
        QUESTION + " Text ," + ANSWER + " Text ," + SCORE + " Text" + ")"

        if (p0 != null) {
            p0.execSQL(CREATE_TABLE)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("Drop table if exists " + DATABASE_NAME)
        onCreate(p0)
    }

    open fun insert(question : String, answer : String){
        val models : SQLiteModels = SQLiteModels()
        val database = this.writableDatabase
        val content = ContentValues()
        content.put("question", question)
        content.put("answer", answer)
        val result = database.insert(TABLE_NAME, null,content)
        if (result.toInt() != -1){
            Log.d("insert", "insert: inserted")
        }else
        {
            Log.d("insert", "insert: Error")
        }
    }

    open fun select (): ArrayList<SQLiteModels>{
        val database = this.readableDatabase

        val list : ArrayList<SQLiteModels> = ArrayList()
        val cursor : Cursor = database.rawQuery(" Select " + ID + " ,"+ QUESTION + " ," + ANSWER + " From " + TABLE_NAME, null)

        if (cursor.moveToFirst()){
            do {
                val models : SQLiteModels = SQLiteModels()
                models.id = cursor.getString(0)
                models.questions = cursor.getString(1)
                models.answer = cursor.getString(2)
                list.add(models)
            }while (cursor.moveToNext())
        }
        return list
    }
    open fun delete(id : String){
        val database = this.writableDatabase

        database.delete(TABLE_NAME, ID + " = ? ", arrayOf(id))
    }
    open fun select_question() : List<String>{
        val database = this.readableDatabase
        val models = mutableListOf<String>()

        val cursor :Cursor = database.rawQuery(" Select $QUESTION From $TABLE_NAME" , null)
        if (cursor.moveToFirst()){
            do {
                val question = cursor.getString(cursor.getColumnIndexOrThrow(QUESTION))
                models.add(question)
            }while (cursor.moveToNext())
        }
        return models
    }

    open fun select_answer() : List<String>{
        val database = this.readableDatabase
        val models = mutableListOf<String>()

        val cursor :Cursor = database.rawQuery(" Select $ANSWER From $TABLE_NAME" , null)
        if (cursor.moveToFirst()){
            do {
                val answer = cursor.getString(cursor.getColumnIndexOrThrow(ANSWER))
                models.add(answer)
            }while (cursor.moveToNext())
        }
        return models
    }
//    open fun select_id() : List<String>{
//        val database = this.readableDatabase
//        val models = mutableListOf<String>()
//
//        val cursor :Cursor = database.rawQuery(" Select $ID From $TABLE_NAME" , null)
//        if (cursor.moveToFirst()){
//            do {
//                val id = cursor.getString(cursor.getColumnIndexOrThrow(ID))
//                models.add(id)
//            }while (cursor.moveToNext())
//        }
//        return models
//    }

}