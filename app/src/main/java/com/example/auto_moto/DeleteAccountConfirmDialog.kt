package com.example.auto_moto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.DialogConfirmDeleteBinding

class DeleteAccountConfirmDialog : DialogFragment() {
    private lateinit var binding: DialogConfirmDeleteBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogConfirmDeleteBinding.inflate(inflater,container,false)

        val dialogWindow = dialog?.window
        dialogWindow?.setBackgroundDrawableResource(R.drawable.background_borders)
        binding.btDelYes.setOnClickListener {
            findNavController().navigate(DeleteAccountConfirmDialogDirections.actionDeleteAccountConfirmFragmentToFinalDeleteAccFragment())
            dismiss()
        }
        binding.btDelNo.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}