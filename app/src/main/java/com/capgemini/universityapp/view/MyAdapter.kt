package com.capgemini.universityapp.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.universityapp.R
import com.capgemini.universityapp.model.Student

class MyAdapter(val data: List<Student>)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameT: TextView
        val marksT: TextView
        val idT: TextView

        init {
            nameT = view.findViewById(R.id.nameT)
            marksT = view.findViewById(R.id.marksT)
            idT = view.findViewById(R.id.idT)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.student_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val std = data[position]
        holder.nameT.text = "${std.firstName} ${std.lastName}"
        holder.marksT.text = "${std.marks}"
        holder.idT.text = "${std.id}"

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, StudentData::class.java)
            intent.putExtra("ID",std.id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = data.size
}