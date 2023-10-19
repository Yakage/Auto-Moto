package com.example.auto_moto

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var db: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        db = DBhelper(requireContext()) // Initialize the database

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }


        binding.btSubmit.setOnClickListener{
            val FullNameTEXT = binding.FullNameET.text.toString()
            val UsernameTEXT = binding.UsernameET.text.toString()
            val ContactINT = binding.ContactET.text.toString()
            val PasswordTEXT = binding.PasswordET.text.toString()
            val ConfirmPasswordTEXT = binding.ConfirmPasswordET.text.toString()

            if (TextUtils.isEmpty(FullNameTEXT) || TextUtils.isEmpty(UsernameTEXT) ||
                TextUtils.isEmpty(ContactINT) || TextUtils.isEmpty(PasswordTEXT) ||
                TextUtils.isEmpty(ConfirmPasswordTEXT)) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (PasswordTEXT != ConfirmPasswordTEXT) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Save data to the database
                val savedata = db.saveuserdata(FullNameTEXT, UsernameTEXT, ContactINT, PasswordTEXT, ConfirmPasswordTEXT)
                if (savedata) {
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
                    Toast.makeText(requireContext(), "Sign Up Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Account Already Exists", Toast.LENGTH_SHORT).show()
                }
            }
        }


        return binding.root
    }
}
