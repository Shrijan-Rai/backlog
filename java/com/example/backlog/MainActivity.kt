package com.example.backlog

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var viewDetail: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get a reference to the framelayout
        val contentLayout = findViewById<FrameLayout>(R.id.frameLayout)

        // Inflate the content layout XML file
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val contentView = inflater.inflate(R.layout.main_content, null)

        val res=contentView.findViewById<TextView>(R.id.textView3)
        res.setOnClickListener{

            startActivity(Intent(applicationContext, Register::class.java))
            true
        }

        viewDetail=contentView.findViewById<MaterialCardView>(R.id.viewDetails)
        viewDetail.setOnClickListener(){
            var intent = Intent(this, ViewDetails::class.java)
            startActivity(intent)
        }



        // Add the content view to the frameLayout
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        contentLayout.addView(contentView, layoutParams)

        drawerLayout  = findViewById(R.id.drawerLayout)


        val navView : NavigationView = findViewById(R.id.nav_view)


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//                var mail = navView.findViewById<TextView>(R.id.gmail)
//        mail.text = FirebaseAuth.getInstance().currentUser!!.email
        navView.setNavigationItemSelectedListener {

         it.isChecked= true

        when(it.itemId){

            R.id.nav_home -> replaceFragment(HomeFragment(),it.title.toString())


//            R.id.nav_message -> replaceFragment(HomeFragment(),it.title.toString())
            R.id.nav_setting -> replaceFragment(SettingFragment(),it.title.toString())
            R.id.nav_message1 -> replaceFragment(MessageFragment(),it.title.toString())
           // R.id.nav_share -> replaceFragment(HomeFragm,it.title.toString())
            R.id.nav_logOut->{

                FirebaseAuth.getInstance().signOut()

//                val clickableArea = navView.findViewById<ListView>(R.id.nav_logOut)
//                clickableArea.setOnClickListener{
//                val intent = Intent(this, SignInActivity::class.java)
//                startActivity(intent)}
                // Handle settings action
                startActivity(Intent(applicationContext, SignInActivity::class.java))
                true
            }



        }
            true

        }


        }

    private fun replaceFragment(fragment: Fragment, string: String){


        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}