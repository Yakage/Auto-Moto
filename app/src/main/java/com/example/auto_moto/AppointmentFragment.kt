package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAppointmentBinding


class AppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding
    private lateinit var databaseHelper: DatabaseHelperForAppointment

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

        databaseHelper = DatabaseHelperForAppointment(requireContext())

        binding.btContinue.setOnClickListener {
            // Get the appointment data from your UI (services, car type, date, time, service charge)
            val services = "Car Wash" // Replace with actual data
            val carType = "Mercedes Benz" // Replace with actual data
            val date = "Fri Oct 20, 2023" // Replace with actual data
            val time = "05 to 06 PM" // Replace with actual data
            val serviceCharge = 20000.0 // Replace with actual data

            // Create an Appointment object with the data
            val appointment = DatabaseHelperForAppointment.AppointmentData(services, carType, date, time, serviceCharge)

            // Insert the appointment into the database
            databaseHelper.insertAppointment(appointment)

            // Optionally, you can show a confirmation message or perform any other actions
            Toast.makeText(requireContext(), "Appointment added successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AppointmentFragmentDirections.actionAppointmentFragmentToConfirmationFragment())
        }
        return binding.root
    }


}