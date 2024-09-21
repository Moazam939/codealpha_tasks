package com.example.flashcardquizapp.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardquizapp.QuizActivity
import com.example.flashcardquizapp.R
import com.example.flashcardquizapp.dataBase.DBHelper
import com.example.flashcardquizapp.models.SQLiteModels

class checkAdaptera(val context: Context, val list: ArrayList<SQLiteModels>) : RecyclerView.Adapter<checkAdaptera.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val id : TextView = itemView.findViewById(R.id.idcount)
        val questioncheck : TextView = itemView.findViewById(R.id.questioncheck)
        val answercheck : TextView = itemView.findViewById(R.id.answercheck)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.questionview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.id.text = list[position].id
        holder.questioncheck.setText(list[position].questions)
        holder.answercheck.setText(list[position].answer)

//        holder.itemView.setOnClickListener {
//            val next  = Intent(context, QuizActivity::class.java)
//            next.putExtra("id", holder.id.text.toString())
//            next.putExtra("questions", holder.questioncheck.text.toString())
////            next.putExtra("answer", holder.answercheck.toString())
//            context.startActivity(next)
//        }

        holder.itemView.setOnLongClickListener {
            val database = DBHelper(context)
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete")
            builder.setMessage("Are you Sure").setPositiveButton("Yes") { dialog: DialogInterface, which: Int ->
                val deleteid = holder.id.text.toString()
                database.delete(deleteid)
                list.removeAt(position)
                notifyItemRemoved(position)
            }.setNegativeButton("No"){
                dialog : DialogInterface, which : Int ->
                dialog.dismiss()
            }
            builder.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}