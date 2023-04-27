package com.example.backlog

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ViewDetails : AppCompatActivity() {



    private lateinit var stuRecyclerView: RecyclerView
    private lateinit var loadingDataTv: TextView
    private lateinit var stuList: ArrayList<Users>
    private lateinit var dbRef: DatabaseReference
    @SuppressLint("MissingInflatedId")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_details)


        stuRecyclerView = findViewById(R.id.viewStudentDetail)
        stuRecyclerView.layoutManager = LinearLayoutManager(this)
        stuRecyclerView.setHasFixedSize(true)
        loadingDataTv = findViewById(R.id.loadData)
        stuList = arrayListOf<Users>()

        getStudentData()
    }


    private fun getStudentData() {
        stuRecyclerView.visibility = View.GONE
        loadingDataTv.visibility = View.VISIBLE
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                stuList.clear()
                if(snapshot.exists()) {
                    for(stuSnap in snapshot.children) {
                        val studentData = stuSnap.getValue(Users::class.java)
                        stuList.add(studentData!!)
                    }
                    val mAdapter = StudentAdapter(stuList)
                    stuRecyclerView.adapter = mAdapter

                    stuRecyclerView.visibility = View.VISIBLE
                    loadingDataTv.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}