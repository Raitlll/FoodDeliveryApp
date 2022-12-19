package com.example.fooddelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class RestaurantActivity : AppCompatActivity() {

    private lateinit var newRecylerview : RecyclerView
    private lateinit var newArrayList : ArrayList<Restaurant>
    lateinit var names : Array<String>
    lateinit var addresses: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        names = arrayOf("McDonalds","Hesburger","Fasters","Sirius","METRO","Burger King")
        addresses = arrayOf("Turu 6, 51014 Tartu","Riia 2, 51004 Tartu","Võru 79, 50112 Tartu","Võru 83, 50112 Tartu","Raatuse 18, 51009 Tartu","Ringtee 75, 50501 Tartu")

        newRecylerview = findViewById(R.id.restaurantList)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)
        newArrayList = arrayListOf<Restaurant>()
        getData()
    }
    private fun getData(){
        for(i in names.indices){
            val restaurants = Restaurant(names[i], addresses[i])
            newArrayList.add(restaurants)
        }
        newRecylerview.adapter = MyAdapter(newArrayList)
    }
}