package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentDeleteAccountConfirmBinding
import com.example.auto_moto.databinding.FragmentFinalDeleteAccBinding
import com.example.auto_motov04.DBhelper

class FinalDeleteAccFragment : Fragment() {

    private lateinit var binding: FragmentFinalDeleteAccBinding
    private lateinit var db: DBhelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinalDeleteAccBinding.inflate(inflater,container,false)
        db = DBhelper(requireContext())

        binding.proceedDelete.setOnClickListener {
            findNavController().navigate(FinalDeleteAccFragmentDirections.actionFinalDeleteAccFragmentToLoginFragment())
        }
        binding.cancelDelete.setOnClickListener {
            findNavController().navigate(FinalDeleteAccFragmentDirections.actionFinalDeleteAccFragmentToAccountFragment())
        }

        return binding.root
    }


}