package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.auto_moto.databinding.FragmentReceiptBinding

class ReceiptFragment : Fragment() {
    private lateinit var binding: FragmentReceiptBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiptBinding.inflate(inflater, container, false)
        return binding.root
    }


}