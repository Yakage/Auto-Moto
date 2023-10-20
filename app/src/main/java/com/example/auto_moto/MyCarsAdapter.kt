package com.example.auto_moto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCarsAdapter(private val myCars: ArrayList<MyCarList>) : RecyclerView.Adapter<MyCarsAdapter.MyViewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_cars_view, parent, false)
        return MyViewsHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewsHolder, position: Int) {
        val currentItems = myCars[position]
        holder.carImage.setImageResource(currentItems.image)
        holder.carName.text = currentItems.name
        holder.carModel.text = currentItems.model
        holder.carNumber.text = currentItems.number
    }
    override fun getItemCount(): Int {
        return myCars.size
    }

    class MyViewsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val carName: TextView = itemView.findViewById(R.id.tv_carName)
        val carModel: TextView = itemView.findViewById(R.id.tv_carModel)
        val carNumber: TextView = itemView.findViewById(R.id.tv_carNumber)
        val carImage: ImageView = itemView.findViewById(R.id.iv_myCar)
    }
}