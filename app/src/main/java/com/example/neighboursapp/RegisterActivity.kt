package com.example.neighboursapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var registerBtn = findViewById<View>(R.id.registerBtn) as Button
        var nameRegistration = findViewById<View>(R.id.nameRegistration) as TextView
        var emailRegistration = findViewById<View>(R.id.emailRegistration) as TextView
        var passwordRegistration = findViewById<View>(R.id.passwordRegistration) as TextView

        registerBtn.setOnClickListener {
            when{
                // if name window is empty, show pop up tex
                TextUtils.isEmpty(nameRegistration.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please, enter your name :)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // if email window is empty, show popup text
                TextUtils.isEmpty(emailRegistration.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please, enter your email :)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // if email window is empty, show popup text
                TextUtils.isEmpty(passwordRegistration.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please, enter your password :)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else ->{
                    //val name: String = nameRegistration.text.toString().trim{it <= ' '}
                    val email: String = emailRegistration.text.toString().trim{ it <= ' '}
                    val password: String = passwordRegistration.text.toString().trim{ it <= ' '}

                    // create an instance and create and register a user with name, email and password.
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener() { task ->
                            //if the registration is successfully done
                            if (task.isSuccessful){
                                //firebase register user
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Toast.makeText(this@RegisterActivity,
                                    "You were successfully registered! :) ",
                                    Toast.LENGTH_SHORT
                                ).show()
                                /**
                                 * Here the new user is automatically signed in,
                                 * so we send him to the main screen
                                 */

                                val intent =
                                    Intent(this@RegisterActivity,MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("userId",firebaseUser.uid)
                                intent.putExtra("emailId",email)
                                startActivity(intent)
                                finish()
                            } else  {
                                // if registration is unsuccessful then show error message.
                                Toast.makeText(this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }


                }
            }
        }
    }


}