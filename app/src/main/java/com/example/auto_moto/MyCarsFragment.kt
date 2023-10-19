package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentMyCarsBinding


class MyCarsFragment : Fragment() {
    private lateinit var binding: FragmentMyCarsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyCarsBinding.inflate(inflater, container, false)
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(MyCarsFragmentDirections.actionMyCarsFragmentToAccountFragment())
        }
        binding.btAddNewCar.setOnClickListener {
            findNavController().navigate(MyCarsFragmentDirections.actionMyCarsFragmentToAddNewCarsFragment())
        }
        return binding.root
    }

}