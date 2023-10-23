package com.example.auto_moto

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.AccountFragmentDirections
import com.example.auto_moto.databinding.FragmentAccountBinding
import com.example.auto_motov04.DBhelper

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var db: DBhelper
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        db = DBhelper(requireContext())

        sharedPref = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Fetch and display user data
        updateUserData()

        binding.editProfile.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToEditProfileFragment())
        }
        binding.tvMyCar.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToMyCarsFragment())
        }

        binding.btDeleteAccount.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToDeleteAccountConfirmFragment())
        }

        return binding.root
    }

    // Function to fetch and display user data
    private fun updateUserData() {
        val username = sharedPref.getString("username", "default_value") ?: "default_value"
        Log.d("AccountFragment", "Retrieved username from SharedPreferences: $username")

        val user = db.getUserData(username)

        if (user != null) {
            // Display user's Name and Email in TextViews
            binding.tvName.text = "Name: ${user.Name}"
            binding.tvEmail.text = "Email: ${user.email}"
            // You can populate other TextViews with user data if needed
        }
    }
}
