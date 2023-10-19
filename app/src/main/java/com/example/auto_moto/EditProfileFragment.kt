package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var db: DBhelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToAccountFragment())
        }
        return binding.root
    }


}