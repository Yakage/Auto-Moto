package com.example.auto_moto

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.auto_moto.databinding.DrawerHeaderBinding

class DrawerHeader : Fragment() {

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.drawer_header, container, false)
        val db = DBhelper(requireContext())
        val cursor = db.getData()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val data = StringBuilder()
                do {
                    val name = cursor.getString(cursor.getColumnIndex("Username"))
                    val email = cursor.getString(cursor.getColumnIndex("Email"))
                    data.append("Name: $name, Email: $email")
                    // Process data here
                } while (cursor.moveToNext())

            }
            cursor.close()
        }
    }


}