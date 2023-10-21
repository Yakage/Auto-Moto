package com.example.auto_moto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController

class DeleteAccountConfirmFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the dialog layout XML
        val view = inflater.inflate(R.layout.fragment_delete_account_confirm, container, false)
        val dialogWindow = dialog?.window
        dialogWindow?.setBackgroundDrawableResource(R.drawable.background_borders)
        val close = view.findViewById<Button>(R.id.bt_delNo)
        val yes = view.findViewById<Button>(R.id.bt_delYes)
        yes.setOnClickListener {
            findNavController().navigate(DeleteAccountConfirmFragmentDirections.actionDeleteAccountConfirmFragmentToFinalDeleteAccFragment())
            dismiss()
        }
        close.setOnClickListener {
            dismiss()
        }

        return view
    }
}