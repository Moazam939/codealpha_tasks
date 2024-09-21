package com.example.fitnesstracker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
val DATA_NAME : String = "setgoals.db"
val TABLE : String = "goals"
val WORK : String = "work"
val VERSION :Int = 1
var IDs : String = "id"
class DBHelper1(context : Context) :SQLiteOpenHelper(context, DATA_NAME, null, VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL(" Create Table " + TABLE + " (" + IDs + " INTEGER PRIMARY KEY AUTOINCREMENT ," + WORK + " Text " + ")")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p0 != null) {
            p0.execSQL("Drop table if exists " + TABLE)
            onCreate(p0)
        }
    }

    open fun insert( work : String){

        val database = this.writableDatabase
        var content = ContentValues()
        content.put("work", work)
        val result = database.insert(TABLE, null,content)

        if (result.toInt() == 0){
            Log.d("Error", "Error")
        }else{
            Log.d("Error", "insert:")
        }
    }
    open fun select() : ArrayList<SQLiteModels>{
        val  database = this.readableDatabase

        val model : ArrayList<SQLiteModels> = ArrayList()

        var cursor : Cursor = database.rawQuery(" Select $IDs, $WORK from " + TABLE , null)
        if (cursor.moveToFirst()) {
            do {
                val lists : SQLiteModels = SQLiteModels()
                lists.id = cursor.getString(0)
                lists.workout1 = cursor.getString(1)
                model .add(lists)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return model
    }
    open fun delete(id : String){
        val database = this.writableDatabase

        val cursor  = database.delete(TABLE, ID + " = ? " , arrayOf(id))
    }
}