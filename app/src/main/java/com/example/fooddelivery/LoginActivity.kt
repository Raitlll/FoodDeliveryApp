package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val loginButton: Button = findViewById(R.id.buttonLogin)
        loginButton.setOnClickListener {
            LogIn()
        }
    }
    private fun LogIn() {

        val email: EditText = findViewById(R.id.loginEmail)
        val password: EditText = findViewById(R.id.loginPassword)

        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill in all the fields",Toast.LENGTH_SHORT).show()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.signInWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                val intent = Intent(this,RestaurantActivity::class.java)
                startActivity(intent)
                Toast.makeText(baseContext,"Logged in!",Toast.LENGTH_SHORT).show()
            }
            else {
                 Toast.makeText(baseContext,"Authentication failed.",Toast.LENGTH_SHORT)
                     .show()
            }
        }.addOnFailureListener {
            Toast.makeText(baseContext,"Authentication failed. ${it.localizedMessage}",Toast.LENGTH_SHORT)
                .show()
            }
    }
}