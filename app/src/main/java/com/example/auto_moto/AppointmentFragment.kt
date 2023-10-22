package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAppointmentBinding


class AppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(AppointmentFragmentDirections.actionAppointmentFragmentToServicesFragment())
        }
        binding.btContinue.setOnClickListener {
            findNavController().navigate(AppointmentFragmentDirections.actionAppointmentFragmentToConfirmationFragment())
        }
        binding.btAddCar.setOnClickListener {
            findNavController().navigate(AppointmentFragmentDirections.actionAppointmentFragmentToAddNewCarsFragment())
        }
        return binding.root
    }


}