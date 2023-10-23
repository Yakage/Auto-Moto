package com.example.auto_moto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentConfirmationBinding

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

        return binding.root
    }

}