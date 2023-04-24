package com.example.backlog




import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager

class PaymentPage : AppCompatActivity() {
    private lateinit var et_upiid: EditText
    private lateinit var et_name: EditText
    private lateinit var makePaymentbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_page)
        et_upiid=findViewById(R.id.idEdtUpi)
        et_name=findViewById(R.id.idEdtName)
        makePaymentbutton=findViewById(R.id.idBtnMakePayment)

        val textInfo = findViewById<TextView>(R.id.text)

        textInfo.text = "Amount to be Paid : \n\nRs 10000 \n\nSubject : ${intent.getStringExtra("subject")}"


        makePaymentbutton.setOnClickListener {
            val upiId: String = et_upiid.text.toString()
            val name: String = et_name.text.toString()

            if (upiId.isEmpty() && name.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please fill all the fields ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout,HomeFragment())
                Toast.makeText(applicationContext, "You re successfully registered for ${intent.getStringExtra("subject")} ", Toast.LENGTH_LONG).show()
//                startActivity(Intent(,HomeFragment::requireActivity))
            }
        }
        }
}