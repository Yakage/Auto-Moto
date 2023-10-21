package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAccountBinding
import com.example.auto_motov04.DBhelper

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var db: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        db = DBhelper(requireContext())

        binding.editProfile.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToEditProfileFragment())
        }
        binding.tvMyCar.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToMyCarsFragment())
        }

        binding.btDeleteAccount.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToDeleteAccountConfirmFragment())
        }

        // Retrieve the user's data
        val username = "Username = ? AND Email = ?"
        val user = db.getUserData(username)

        // Set the user's name and email in TextViews
        binding.tvName.text = user?.Name
        binding.tvEmail.text = user?.email

        return binding.root
    }
}
