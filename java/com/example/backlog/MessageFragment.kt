package com.example.backlog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*



class MessageFragment : Fragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var listener: ValueEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_message, container, false)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Users")

        // Add a listener to fetch data from the database
        listener = myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method will be called whenever the data at the specified database reference changes.
                // You can access the data using the snapshot parameter.
                val data = snapshot.getValue(Users::class.java)

                // Display the fetched data in the XML layout
                displayData(data)
            }

            override fun onCancelled(error: DatabaseError) {
                // This method will be called if there is an error while reading the data.
                // You can handle the error here.
            }
        })
        return v
    }


    override fun onDestroy() {
        super.onDestroy()

        // Remove the listener to avoid any memory leaks
        myRef.removeEventListener(listener)
    }

    private fun displayData(data: Users?) {
        // Inflate the layout file
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_message, null)

        // Get references to the TextView elements
        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val ageTextView = view.findViewById<TextView>(R.id.courseTextView)
        val emailTextView = view.findViewById<TextView>(R.id.courseCodeTextView)

        // Set the values of the TextView elements with the fetched data
        nameTextView.text = data?.name
        ageTextView.text = "Course ${data?.course}"
        emailTextView.text = "Course Code: ${data?.courseCode}"

        // Add the view to the activity
        val container = view.findViewById<LinearLayout>(R.id.linear)
        container.addView(view)

    }

}