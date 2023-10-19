package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.auto_moto.databinding.FragmentGarageBinding
import com.example.auto_moto.databinding.FragmentHomeBinding

class GarageFragment : Fragment() {
    private lateinit var binding: FragmentGarageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGarageBinding.inflate(inflater,container,false)

        return binding.root
    }


}