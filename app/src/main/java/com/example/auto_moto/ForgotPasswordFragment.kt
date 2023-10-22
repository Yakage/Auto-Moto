package com.example.auto_moto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentForgotpasswordBinding
import com.example.auto_motov04.DBhelper

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotpasswordBinding
    private lateinit var db: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotpasswordBinding.inflate(inflater, container, false)
        db = DBhelper(requireContext())

        binding.tvLogin.setOnClickListener() {
            findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
        }
        binding.tvSignup.setOnClickListener() {
            findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToSignUpFragment())
        }

        // Set an OnClickListener for IbSendEmail to send the email code (reset code)
        binding.IbSendEmail.setOnClickListener {
            val email = binding.TvEmail.text.toString()

            if (ValidationUtils.isTextNotEmpty(email)) {
                // Generate a reset code here (you can use a function for this)
                val resetCode = generateResetCode() // Call your code generation function here

                // Send the reset code via email
                if (db.sendResetCodeByEmail(requireContext(), email, resetCode)) {
                    Toast.makeText(requireContext(), "Reset code sent successfully.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to send reset code. Please try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please provide your email address.", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btReset.setOnClickListener {
            val email = binding.TvEmail.text.toString()
            val resetCode = binding.etResetCode.text.toString()
            val newPassword = binding.etNewPassword.text.toString()

            // Check if the reset code is correct (compare with the one you sent earlier)
            if (ValidationUtils.isTextNotEmpty(email) &&
                ValidationUtils.isTextNotEmpty(resetCode) &&
                ValidationUtils.isTextNotEmpty(newPassword)
            ) {
                if (db.updatePassword(requireContext(), email, newPassword)) {
                    Toast.makeText(requireContext(), "Password reset successfully.", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
                } else {
                    Toast.makeText(requireContext(), "Failed to reset password. Please try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please Fill the Required Fields", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

    // Function to generate a reset code (you can customize this logic)
    private fun generateResetCode(): String {
        val charset = "0123456789" // Characters to include in the code
        val codeLength = 6 // Length of the reset code

        val random = java.util.Random()
        val resetCode = StringBuilder()

        for (i in 0 until codeLength) {
            val randomIndex = random.nextInt(charset.length)
            val randomChar = charset[randomIndex]
            resetCode.append(randomChar)
        }

        return resetCode.toString()
    }
}


