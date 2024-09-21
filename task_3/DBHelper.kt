package com.example.fitnesstracker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

val DATA_BASE : String = "fitness.db"
val TABLE_NAME : String = "fitness"
val WORK_NAME : String = "workout"
val VERSION_NUMBER :Int = 1
var ID : String = "id"
class DBHelper(context : Context) :SQLiteOpenHelper(context, DATA_BASE, null, VERSION_NUMBER ) {
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL(" Create Table " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + WORK_NAME + " Text " + ")")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p0 != null) {
            p0.execSQL("Drop table if exists " + TABLE_NAME)
            onCreate(p0)
        }
    }

    open fun insert( workname : String){

        val database = this.writableDatabase
        var content = ContentValues()
        content.put("workout", workname)
        val result = database.insert(TABLE_NAME, null,content)

        if (result.toInt() == 0){
            Log.d("Error", "Error")
        }else{
            Log.d("Error", "insert:")
        }
    }
    open fun select() : ArrayList<SQLiteModels>{
        val  database = this.readableDatabase

        val models : ArrayList<SQLiteModels> = ArrayList()

        var cursor : Cursor = database.rawQuery(" Select $ID, $WORK_NAME from " + TABLE_NAME , null)
        Log.d("Database", "Number of rows: ${cursor.count}")
        if (cursor.moveToFirst()) {
           do {
                val list : SQLiteModels = SQLiteModels()
               list.id = cursor.getString(0)
               list.workout1 = cursor.getString(1)
               models.add(list)
           } while (cursor.moveToNext())
       }
        cursor.close()
        return models
    }
    open fun delete(id : String){
        val database = this.writableDatabase

        val cursor  = database.delete(TABLE_NAME, ID + " = ? " , arrayOf(id))
    }
}