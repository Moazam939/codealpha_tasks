package com.example.fitnesstracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapters(var context: Context, var list: ArrayList<SQLiteModels>) : RecyclerView.Adapter<Adapters.ViewHolder>() {
    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        var workout : TextView = item.findViewById(R.id.jogging)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(context).inflate(R.layout.addworkoutrecycle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.workout.text = list[position].workout1

        holder.itemView.setOnClickListener {
            val next = Intent(context, workoutActivity::class.java)
            next.putExtra("tracker", holder.workout.text.toString())

            context.startActivity(next)
        }
//        holder.itemView.setOnLongClickListener {
//            val helper = DBHelper(context)
//            helper.delete()
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}