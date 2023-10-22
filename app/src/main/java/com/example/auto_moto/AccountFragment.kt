package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAccountBinding

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

        binding.tvLogOut.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToLoginFragment())
        }
        // Retrieve the user's data
        val userInfo = "Username =  AND Email = "
        val user = db.getUserData(userInfo)


        // Set the user's name and email in TextViews
        binding.tvName.text = user?.Name
        binding.tvEmail.text = user?.email

        return binding.root
    }
}
