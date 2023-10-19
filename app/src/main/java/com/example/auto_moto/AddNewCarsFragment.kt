package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAddNewCarsBinding


class AddNewCarsFragment : Fragment() {
    private lateinit var binding: FragmentAddNewCarsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNewCarsBinding.inflate(inflater, container, false)
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(AddNewCarsFragmentDirections.actionAddNewCarsFragmentToMyCarsFragment())
        }
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(AddNewCarsFragmentDirections.actionAddNewCarsFragmentToAppointmentFragment())
        }
        return binding.root
    }


}