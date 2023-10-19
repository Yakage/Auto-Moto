package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentLoginBinding
import java.util.*

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var db: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        db = DBhelper(requireContext()) // Initialize DB helper

        binding.btLogin.setOnClickListener {
            loginUser()
        }

        binding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
        binding.tvForgotPassword.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())


        }

        return binding.root
    }

    private fun loginUser() {
        val usernameText = binding.etUsernam.text.toString()
        val passwordText = binding.etPasswor.text.toString()

        if (ValidationUtils.isTextNotEmpty(usernameText) && ValidationUtils.isTextNotEmpty(passwordText)) {
            val isSuccess = db.loginUser(usernameText, passwordText)
            if (isSuccess) {
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                Toast.makeText(requireContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Please Fill the Required Fields", Toast.LENGTH_SHORT).show()
        }
    }


}
