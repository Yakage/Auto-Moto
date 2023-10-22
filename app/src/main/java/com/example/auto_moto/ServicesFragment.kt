package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentServicesBinding


class ServicesFragment : Fragment() {
    private lateinit var binding: FragmentServicesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServicesBinding.inflate(inflater, container, false)
        val normalColor = resources.getColor(R.color.card_normal_color)
        val clickedColor = resources.getColor(R.color.card_clicked_color)
        binding.cvCarRepair.setOnClickListener {
            val currentColor = binding.cvCarRepair.cardBackgroundColor.defaultColor
            val bundle = Bundle()
            var newColor = currentColor
            if (currentColor == normalColor) {
                newColor = clickedColor
                bundle.putString("price", "PHP 5,000")
            } else {
                newColor = normalColor
            }
            val fragment = ConfirmationFragment()
            fragment.arguments = bundle
            binding.cvCarRepair.setCardBackgroundColor(newColor)
        }
        binding.ibBackArrow.setOnClickListener{
            findNavController().navigate(ServicesFragmentDirections.actionServicesFragmentToHomeFragment())
        }
        binding.btProceed.setOnClickListener{
            findNavController().navigate(ServicesFragmentDirections.actionServicesFragmentToAppointmentFragment())
        }
        return binding.root
    }

}