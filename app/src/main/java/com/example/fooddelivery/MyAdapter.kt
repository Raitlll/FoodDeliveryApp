package com.example.fooddelivery

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val restaurantList: ArrayList<Restaurant>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = restaurantList[position]
        holder.name.text = currentItem.name
        holder.address.text = currentItem.address
        val context = holder.order.context
        holder.order.setOnClickListener {
            var intent:Intent = Intent(context,MenuActivity::class.java)
            intent.putExtra("restaurant",holder.name.text)
            intent.putExtra("address",holder.address.text)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.restaurantName)
        val address: TextView = itemView.findViewById(R.id.restaurantAddress)
        val order: Button = itemView.findViewById(R.id.OrderButton)

    }


}