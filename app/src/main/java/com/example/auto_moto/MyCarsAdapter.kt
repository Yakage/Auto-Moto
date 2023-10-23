package com.example.auto_moto

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyCarsAdapter(private val myCars: ArrayList<MyCarList>,private val myCarsFragment: MyCarsFragment) : RecyclerView.Adapter<MyCarsAdapter.MyViewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_cars_view, parent, false)
        return MyViewsHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewsHolder, position: Int) {
        val currentItems = myCars[position]
        val carImageBitmap = BitmapFactory.decodeByteArray(currentItems.image, 0, currentItems.image.size)
        holder.carImage.setImageBitmap(carImageBitmap)
        holder.carName.text = currentItems.name
        holder.carModel.text = currentItems.model
        holder.carNumber.text = currentItems.brand
        // Add an OnClickListener for the delete button (assuming it's an ImageButton with ID R.id.ib_deleteCar)
        holder.bImage.setOnClickListener {
            showDialog()
        // Get the car details from the current item
            val carName = currentItems.name
            val carModel = currentItems.model
            val carBrand = currentItems.brand
            // Call the deleteCar function in the MyCarsFragment
            myCarsFragment.deleteCar(carName, carModel, carBrand)
        }
    }
    override fun getItemCount(): Int {
        return myCars.size
    }


    class MyViewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carName: TextView = itemView.findViewById(R.id.tv_carName)
        val carModel: TextView = itemView.findViewById(R.id.tv_carModel)
        val carNumber: TextView = itemView.findViewById(R.id.tv_carNumber)
        val carImage: ImageView = itemView.findViewById(R.id.iv_myCar)
        val bImage: ImageButton = itemView.findViewById(R.id.ib_delCar)

    }

    private fun showDialog(){

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<MyCarList>) {
        myCars.clear()
        myCars.addAll(newData)
        notifyDataSetChanged()
    }

}
