package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentFinalDeleteAccBinding

class FinalDeleteAccFragment : Fragment() {

    private lateinit var binding: FragmentFinalDeleteAccBinding
    private lateinit var db: DBhelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinalDeleteAccBinding.inflate(inflater,container,false)
        db = DBhelper(requireContext())

        binding.proceedDelete.setOnClickListener {
            AccDel()
        }
        binding.cancelDelete.setOnClickListener {
            findNavController().navigate(FinalDeleteAccFragmentDirections.actionFinalDeleteAccFragmentToAccountFragment())
        }

        return binding.root
    }

    private fun AccDel(){
        val userName = binding.confirmEmail.text.toString()
        val passWord = binding.confirmPassOne.text.toString()
        val CpassWord = binding.confirmPassTwo.text.toString()

        if(ValidationUtils.isTextNotEmpty(userName) || ValidationUtils.isTextNotEmpty(passWord) || ValidationUtils.isTextNotEmpty(CpassWord)){
            val success = db.deleteAccount(userName)
            if (success){
                Toast.makeText(requireContext(), "Delete Success", Toast.LENGTH_SHORT).show()
              findNavController().navigate(FinalDeleteAccFragmentDirections.actionFinalDeleteAccFragmentToLoginFragment())
            }else{
                Toast.makeText(requireContext(), "Delete Failed", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Please Fill The Required Fields", Toast.LENGTH_SHORT).show()
        }


    }


}