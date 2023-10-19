package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        binding.editProfile.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToEditProfileFragment())
        }
        binding.tvMyCar.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToMyCarsFragment())
        }
        return binding.root
    }


}