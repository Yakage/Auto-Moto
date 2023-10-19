package com.example.auto_moto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentForgotpasswordBinding


class ForgotPasswordFragment : Fragment() {
        private lateinit var binding: FragmentForgotpasswordBinding
        private lateinit var db: DBhelper

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = FragmentForgotpasswordBinding.inflate(inflater, container, false)
            db = DBhelper(requireContext())

            binding.tvLogin.setOnClickListener(){
                findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
            }
            binding.tvSignup.setOnClickListener(){
                findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToSignUpFragment())
            }

            binding.btReset.setOnClickListener {
                val contact = binding.TvContact.text.toString()
                val resetCode = binding.etResetCode.text.toString()
                val newPassword = binding.etNewPassword.text.toString()

                // Check if the reset code is correct (compare with the one you sent earlier)
             if (ValidationUtils.isTextNotEmpty(contact) || (ValidationUtils.isTextNotEmpty(resetCode)) || (ValidationUtils .isTextNotEmpty(newPassword))){

                 if (db.resetPassword(contact, newPassword, resetCode)) {
                     val isReset = db.updatePassword(contact,newPassword)
                     if(isReset){
                    Toast.makeText(requireContext(), "Password reset successfully.", Toast.LENGTH_SHORT).show()
                    // Navigate to the login fragment
                    findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
                }} else {
                    // Password reset failed
                    Toast.makeText(requireContext(), "Failed to reset password. Please try again.", Toast.LENGTH_SHORT).show()
                }}else{
                    Toast.makeText(requireContext(), "Please Fill the Required Fields", Toast.LENGTH_SHORT).show()
                }
            }

            return binding.root
        }
}

