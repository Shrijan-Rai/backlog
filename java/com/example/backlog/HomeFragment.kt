package com.example.backlog

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class HomeFragment : Fragment() {

    private var subject : EditText? = null
    private lateinit var checkBox: CheckBox

    private lateinit var payment:Button
    private lateinit var termSpinner:Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        subject = view.findViewById(R.id.subject)
        var termSelected :String =""
        payment = view.findViewById<Button>(R.id.payBtn)
        termSpinner = view.findViewById<Spinner>(R.id.term)
        val term = arrayOf("Term 1", "Term 2", "Term 3", "Term 4", "Term 5", "Term 6", "Term 7", "Term 8")

        if (termSpinner != null){
            val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,term)
            termSpinner.adapter = adapter
            termSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    termSelected = term[position]
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

        }

        payment.setOnClickListener {
                if (checkBox.isChecked && subject != null){
                    Toast.makeText(requireActivity(), termSelected, Toast.LENGTH_SHORT).show()
                    val i = Intent(requireActivity(),PaymentPage::class.java)
                    val sub = subject!!.text.toString()
                    i.putExtra("subject",sub)
                    startActivity(i)
                }else{
                    Toast.makeText(requireActivity(), "Please tick the checkbox before proceeding...", Toast.LENGTH_SHORT).show()
                }
                }
        return view










    }




//    class MainActivity : AppCompatActivity() {
//        private var subject : EditText? = null
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_main)
//            val checkBox = findViewById<CheckBox>(R.id.checkBox)
//            subject = findViewById(R.id.subject)
//            var termSelected :String =""
//            val payment = findViewById<Button>(R.id.payBtn)
//            val termSpinner = findViewById<Spinner>(R.id.term)
//            val term = arrayOf("Term 1", "Term 2", "Term 3", "Term 4", "Term 5", "Term 6", "Term 7", "Term 8")


//            if (termSpinner != null){
//                val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,term)
//                termSpinner.adapter = adapter
//                termSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                        termSelected = term[position]
//                    }
//                    override fun onNothingSelected(p0: AdapterView<*>?) {}
//                }
//
//            }

//            payment.setOnClickListener {
//                if (checkBox.isChecked && subject != null){
//                    Toast.makeText(applicationContext, termSelected, Toast.LENGTH_SHORT).show()
//                    val i = Intent(applicationContext,PaymentPage::class.java)
//                    val sub = subject!!.text.toString()
//                    i.putExtra("subject",sub)
//                    startActivity(i)
//                }else{
//                    Toast.makeText(applicationContext, "Please tick the checkbox before proceeding...", Toast.LENGTH_SHORT).show()
//                }
//                       }
//                }
//    }


}