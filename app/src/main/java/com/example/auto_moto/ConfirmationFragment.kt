package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentConfirmationBinding
import com.example.auto_motov04.ConfirmationFragmentDirections

class ConfirmationFragment : Fragment() {

    private lateinit var binding: FragmentConfirmationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        binding.btConfirmation.setOnClickListener {
            findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToReceiptFragment())
        }
        return binding.root
    }


}