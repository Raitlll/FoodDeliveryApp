package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            SignUp()
        }


    }
    private fun SignUp() {
        val email = findViewById<EditText>(R.id.inputEmail)
        val password = findViewById<EditText>(R.id.inputPassword)

        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext,"Success.",Toast.LENGTH_SHORT)
                        .show()
                }
                else {
                    Toast.makeText(baseContext,"Authentication failed.",Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}