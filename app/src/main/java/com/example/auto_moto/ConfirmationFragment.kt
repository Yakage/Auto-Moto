package com.example.auto_moto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentConfirmationBinding
    private lateinit var databaseHelper: DatabaseHelperForAppointment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        val receivedDate = arguments?.getString("calendar")
        val receivedTime = arguments?.getString("selectedTime")


        Log.d("ConfirmationFragment", "receivedDate: $receivedDate")
        Log.d("ConfirmationFragment", "receivedTime: $receivedTime")


        binding.tvDate.text = receivedDate
        binding.tvTime.text = receivedTime


        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToAppointmentFragment())
        }
        binding.btPayNow.setOnClickListener {
            findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToReceiptFragment())
        }

        // Initialize the DatabaseHelper
        databaseHelper = DatabaseHelperForAppointment(requireContext())


        // Retrieve data from the database (replace with your database schema)

        val appointmentData = databaseHelper.getAppointmentById(0)

        // Populate TextViews with retrieved data
        if (appointmentData != null) {
            binding.tvTypesOfServices.text = appointmentData.services
        }
        if (appointmentData != null) {
            binding.tvMyCar.text = appointmentData.carType
        }
        if (appointmentData != null) {
            binding.tvDate.text = appointmentData.date
        }
        if (appointmentData != null) {
            binding.tvTime.text = appointmentData.time
        }
        if (appointmentData != null) {
            binding.serviceCharge.text = "PHP ${appointmentData.serviceCharge}"
        }

        // Handle the "Pay Now" button click event if needed
        binding.btPayNow.setOnClickListener {
            // Implement your payment logic here
            findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToReceiptFragment())
        }

        return binding.root
    }

}