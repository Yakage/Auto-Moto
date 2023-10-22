package com.example.auto_moto

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentLoginBinding
import com.example.auto_motov04.DBhelper

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var db: DBhelper
    private lateinit var sharedPref: SharedPreferences

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
        val usernameText = binding.etUser.text.toString()
        val passwordText = binding.etPass.text.toString()

        if (ValidationUtils.isTextNotEmpty(usernameText) && ValidationUtils.isTextNotEmpty(passwordText)) {
            val isSuccess = db.loginUser(usernameText, passwordText)
            if (isSuccess) {
                // Store the username in SharedPreferences
                sharedPref = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", usernameText)
                editor.apply()
                Log.d("LoginFragment", "Stored username in SharedPreferences: $usernameText")

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
