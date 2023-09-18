package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

abstract class TodoAdapter(val list: List<ToDoModel>):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false))
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }
    class TodoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(todoModel: ToDoModel) {
            with(itemView){
                val colors=resources.getIntArray(R.array.random_color)
                val randomColor=colors[Random().nextInt(colors.size )]
                val viewColorTag=findViewById<View>(R.id.viewColorTag)
                viewColorTag.setBackgroundColor(randomColor)

                val txtShowTitle=findViewById<TextView>(R.id.txtShowTitle)
                val txtShowTask=findViewById<TextView>(R.id.txtShowTask)
                val txtShowCategory=findViewById<TextView>(R.id.txtShowCategory)
                val txtShowTime=findViewById<TextView>(R.id.txtShowTime)
                val txtShowDate=findViewById<TextView>(R.id.txtShowDate)

                txtShowTitle.text=todoModel.title
                txtShowTask.text=todoModel.description
                txtShowCategory.text=todoModel.category

                val myFormat="hh:mm aa"
                val sdf= SimpleDateFormat(myFormat, Locale.ENGLISH)
                txtShowTime.text=sdf.format(Date(todoModel.time))

                val myFormatDate="EEE, dd MMM yyyy"
                val sdfDate=SimpleDateFormat(myFormatDate, Locale.ENGLISH)
                txtShowDate.text=sdfDate.format(Date(todoModel.time))


            }
        }
    }
}