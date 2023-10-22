
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
import com.example.auto_motov04.DBhelper

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
        binding.tvUsername.setText(user?.username)
        binding.tvGmail.setText(user?.email)

        //Back to the Account Fragment
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToAccountFragment())
        }
        //Update the required data
        binding.btUpdate.setOnClickListener {
            val newContact = binding.etContact.text.toString()
            val newEmail = binding.etEmailAddress.text.toString()
            val userName = binding.etEditUserName.text.toString()
            if (ValidationUtils.isTextNotEmpty(newContact) && ValidationUtils.isTextNotEmpty(newEmail) && ValidationUtils.isTextNotEmpty(userName)){
                val User = db.getUserData(username)
                Log.d("Debug", "Updating user data - Username: $username, newEmail: $newEmail, newContact: $newContact")
                if (User != null) {
                    // Update the user's data in the database
                    val isUpdateSuccessful = db.updateUserData(requireContext(), username, newContact, newEmail)

                    if (isUpdateSuccessful) {
                        // Update SharedPreferences with new username and email
                        val editor = sharedPref.edit()
                        editor.putString("username", username)
                        editor.putString("email", newEmail)
                        editor.apply()
                        Toast.makeText(requireContext(), "Edit Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToAccountFragment())
                    } else {
                        Log.d("Debug", "Edit Failed")
                        Toast.makeText(requireContext(), "Edit Failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "User not found in the database", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Please Fill The Required Fields", Toast.LENGTH_SHORT).show()
            }}

        return binding.root
    }
}