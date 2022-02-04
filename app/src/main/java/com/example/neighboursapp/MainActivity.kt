package com.example.neighboursapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user_id = findViewById<View>(R.id.user_id) as TextView
        var email_id = findViewById<View>(R.id.email_id) as TextView
        var btn_logout = findViewById<View>(R.id.btn_logout) as Button

        val userId = intent.getStringExtra("userId")
        val emailId = intent.getStringExtra("emailId")

        user_id.text = "User ID:: $userId"
        email_id.text = "Email ID:: $emailId"

        btn_logout.setOnClickListener {
            //logout from app when clicked

            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, LogInActivity::class.java))
            finish()
        }

    }
}