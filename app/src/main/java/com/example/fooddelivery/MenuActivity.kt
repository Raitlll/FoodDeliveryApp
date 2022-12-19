package com.example.fooddelivery

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.math.log

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var name: TextView = findViewById(R.id.textViewName)
        name.text = intent.getStringExtra("restaurant").toString()
        var address: TextView = findViewById(R.id.textViewAddress)
        address.text = intent.getStringExtra("address").toString()
        var quantity1: EditText = findViewById(R.id.editTextNumber)
        var quantity2: EditText = findViewById(R.id.editTextNumber2)
        var quantity3: EditText = findViewById(R.id.editTextNumber3)
        var quantity4: EditText = findViewById(R.id.editTextNumber4)
        var quantity5: EditText = findViewById(R.id.editTextNumber5)
        var quantity6: EditText = findViewById(R.id.editTextNumber6)
        var finalSum: TextView = findViewById(R.id.totalSum)
        var total = 0;
        quantity1.onFocusChangeListener = View.OnFocusChangeListener{ _,hasFocus ->
            if (!hasFocus) {
                total = Integer.parseInt(quantity1.text.toString()) * 2 +
                        Integer.parseInt(quantity2.text.toString()) * 3 +
                Integer.parseInt(quantity3.text.toString()) * 4 +
                Integer.parseInt(quantity4.text.toString()) * 5 +
                Integer.parseInt(quantity5.text.toString()) * 2 +
                Integer.parseInt(quantity6.text.toString()) * 3
                finalSum.text = "Total sum:  " + total.toString()
            }
        }
        quantity2.onFocusChangeListener = View.OnFocusChangeListener{ _,hasFocus ->
            if (!hasFocus) {
                total = Integer.parseInt(quantity1.text.toString()) * 2 +
                        Integer.parseInt(quantity2.text.toString()) * 3 +
                        Integer.parseInt(quantity3.text.toString()) * 4 +
                        Integer.parseInt(quantity4.text.toString()) * 5 +
                        Integer.parseInt(quantity5.text.toString()) * 2 +
                        Integer.parseInt(quantity6.text.toString()) * 3
                finalSum.text = "Total sum:  " + total.toString()
            }
        }
        quantity3.onFocusChangeListener = View.OnFocusChangeListener{ _,hasFocus ->
            if (!hasFocus) {
                total = Integer.parseInt(quantity1.text.toString()) * 2 +
                        Integer.parseInt(quantity2.text.toString()) * 3 +
                        Integer.parseInt(quantity3.text.toString()) * 4 +
                        Integer.parseInt(quantity4.text.toString()) * 5 +
                        Integer.parseInt(quantity5.text.toString()) * 2 +
                        Integer.parseInt(quantity6.text.toString()) * 3
                finalSum.text = "Total sum:  " + total.toString()
            }
        }
        quantity4.onFocusChangeListener = View.OnFocusChangeListener{ _,hasFocus ->
            if (!hasFocus) {
                total = Integer.parseInt(quantity1.text.toString()) * 2 +
                        Integer.parseInt(quantity2.text.toString()) * 3 +
                        Integer.parseInt(quantity3.text.toString()) * 4 +
                        Integer.parseInt(quantity4.text.toString()) * 5 +
                        Integer.parseInt(quantity5.text.toString()) * 2 +
                        Integer.parseInt(quantity6.text.toString()) * 3
                finalSum.text = "Total sum:  " + total.toString()
            }
        }
        quantity5.onFocusChangeListener = View.OnFocusChangeListener{ _,hasFocus ->
            if (!hasFocus) {
                total = Integer.parseInt(quantity1.text.toString()) * 2 +
                        Integer.parseInt(quantity2.text.toString()) * 3 +
                        Integer.parseInt(quantity3.text.toString()) * 4 +
                        Integer.parseInt(quantity4.text.toString()) * 5 +
                        Integer.parseInt(quantity5.text.toString()) * 2 +
                        Integer.parseInt(quantity6.text.toString()) * 3
                finalSum.text = "Total sum:  " + total.toString()
            }
        }
        quantity6.onFocusChangeListener = View.OnFocusChangeListener{ _,hasFocus ->
            if (!hasFocus) {
                total = Integer.parseInt(quantity1.text.toString()) * 2 +
                        Integer.parseInt(quantity2.text.toString()) * 3 +
                        Integer.parseInt(quantity3.text.toString()) * 4 +
                        Integer.parseInt(quantity4.text.toString()) * 5 +
                        Integer.parseInt(quantity5.text.toString()) * 2 +
                        Integer.parseInt(quantity6.text.toString()) * 3
                finalSum.text = "Total sum:  " + total.toString()
            }
        }

        finalSum.text = "Total sum:  " + total.toString()

        var proceed: Button = findViewById(R.id.ContinueButton)
        proceed.setOnClickListener {
            if (total != 0){
                var intent: Intent = Intent(this,DeliveryActivity::class.java)
                intent.putExtra("total",total)
                intent.putExtra("restaurant",name.text)
                intent.putExtra("address",address.text)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"You have to order something to proceed",Toast.LENGTH_SHORT).show()

            }
        }
    }


}