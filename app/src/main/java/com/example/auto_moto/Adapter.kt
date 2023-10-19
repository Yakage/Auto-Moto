package com.example.auto_moto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val carList: ArrayList<CarItems>)  : RecyclerView.Adapter<Adapter.MyViewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_view, parent, false)
        return MyViewsHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewsHolder, position: Int) {
        val currentItems = carList[position]
        holder.itemImg.setImageResource(currentItems.titleImage)
        holder.itemName.text = currentItems.heading
    }
    override fun getItemCount(): Int {
        return carList.size
    }

    class MyViewsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.tv_itemName)
        val itemImg: ImageView = itemView.findViewById(R.id.iv_items)
    }
}