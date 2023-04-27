package com.example.backlog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class StudentAdapter( private val studentList: ArrayList<Users>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.studentTv.text = "Registration ID: " + currentStudent.regNo+"\nName: " + currentStudent.name+"\n"+"Course: "+currentStudent.course+"\n"+"Course Code: "+currentStudent.courseCode
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentTv: TextView = itemView.findViewById(R.id.studentName)
    }



    }