package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {
    private lateinit var binding: FragmentServicesBinding

    // Define colors for card backgrounds (modify these according to your color resources)
    private val normalColor = R.color.card_normal_color
    private val clickedColor = R.color.card_clicked_color

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicesBinding.inflate(inflater, container, false)

        // Set click listeners for the CardViews
        setCardViewClickListener(binding.cvCarRepair, "PHP 5,000")
        setCardViewClickListener(binding.cvCarTuning, "PHP 1,500")
        setCardViewClickListener(binding.cvAcService, "PHP 5,000")
        setCardViewClickListener(binding.cvTireCare, "PHP 5,000")
        setCardViewClickListener(binding.cvCarPaint, "PHP 30,000")
        setCardViewClickListener(binding.cvCarWash, "PHP 1,000")
        setCardViewClickListener(binding.cvPartsReplace, "PHP 1,000")
        setCardViewClickListener(binding.cvEngineCare, "PHP 25,000")
        setCardViewClickListener(binding.cvWindowTint, "PHP 25,000")

        // Set click listener for the "Proceed" button
        binding.btProceed.setOnClickListener {
            findNavController().navigate(ServicesFragmentDirections.actionServicesFragmentToAppointmentFragment())
        }

        // Set click listener for the back arrow
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(ServicesFragmentDirections.actionServicesFragmentToHomeFragment())
        }

        return binding.root
    }

    // Inside the setCardViewClickListener function
    private fun setCardViewClickListener(cardView: CardView, price: String) {
        cardView.setOnClickListener {
            val currentColor = cardView.cardBackgroundColor.defaultColor
            val bundle = Bundle()
            val newColor = if (currentColor == resources.getColor(normalColor)) resources.getColor(clickedColor)
                           else resources.getColor(normalColor)
            bundle.putString("price", price)
            val fragment = ConfirmationFragment()
            fragment.arguments = bundle
            cardView.setCardBackgroundColor(newColor)
        }
    }

}
