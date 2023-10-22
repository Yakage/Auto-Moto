package com.example.auto_moto

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var db: DBhelper
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        // Initialize the database helper and SharedPreferences
        db = DBhelper(requireContext())
        sharedPref = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Retrieve the username from SharedPreferences
        val username = sharedPref.getString("username", "default_value") ?: "default_value"

        // Fetch the user data from the database
        val user = db.getUserData(username)



// Update EditTexts with user data (if user data is not null)
        binding.etEditUserName.setText(user?.username)
        binding.etEmailAddress.setText(user?.email)
        user?.contact?.let { binding.etContact.setText(it) }


        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToAccountFragment())
        }

        binding.btUpdate.setOnClickListener {
            val username = binding.etEditUserName.text.toString()
            val newContact = binding.etContact.text.toString()
            val newEmail = binding.etEmailAddress.text.toString() // Get new email value

            Log.d("Debug", "Updating user data - Username: $username, newEmail: $newEmail, newContact: $newContact")

            // Update the user's data in the database
            val isUpdateSuccessful = db.updateUserData(requireContext(), username,newContact,newEmail)

            if (isUpdateSuccessful) {
                binding.tvUsername.text = username
                binding.tvGmail.text = newEmail
                Log.d("Debug", "Edit Success")
                Toast.makeText(requireContext(), "Edit Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToAccountFragment())
            } else {
                Log.d("Debug", "Edit Failed")
                Toast.makeText(requireContext(), "Edit Failed", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }
}
