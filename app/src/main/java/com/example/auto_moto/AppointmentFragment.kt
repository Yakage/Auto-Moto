package com.example.auto_moto

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAppointmentBinding
import kotlin.time.ExperimentalTime


class AppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding
    private var calendar: String? = null
    private var selectedTime: String? = null
    private var selectedCar: String? = null
    private val normalColor = R.color.card_normal_color
    private val clickedColor = R.color.card_clicked_color

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(AppointmentFragmentDirections.actionAppointmentFragmentToServicesFragment())
        }
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        calendar = sharedPreferences.getString("calendar", null)
        selectedTime = sharedPreferences.getString("selectedTime", null)
        selectedCar = sharedPreferences.getString("selectedCar", null)
        // Find the CardViews for each time slot by their IDs
        val timeSlot1 = binding.root.findViewById<CardView>(R.id.time_slot_1)
        val timeSlot2 = binding.root.findViewById<CardView>(R.id.time_slot_2)
        val timeSlot3 = binding.root.findViewById<CardView>(R.id.time_slot_3)
        val timeSlot4 = binding.root.findViewById<CardView>(R.id.time_slot_4)
        val timeSlot5 = binding.root.findViewById<CardView>(R.id.time_slot_5)
        val timeSlot6 = binding.root.findViewById<CardView>(R.id.time_slot_6)
        val timeSlot7 = binding.root.findViewById<CardView>(R.id.time_slot_7)
        val timeSlot8 = binding.root.findViewById<CardView>(R.id.time_slot_8)

        // Set up click listeners for each time slot
        setCardViewClickListener(timeSlot1, "9 to 10am")
        setCardViewClickListener(timeSlot2, "10am to 11am")
        setCardViewClickListener(timeSlot3, "11am to 12pm")
        setCardViewClickListener(timeSlot4, "12pm to 1pm")
        setCardViewClickListener(timeSlot5, "1pm to 2pm")
        setCardViewClickListener(timeSlot6, "2pm to 3pm")
        setCardViewClickListener(timeSlot7, "4pm to 5pm")
        setCardViewClickListener(timeSlot8, "5pm to 6pm")

        binding.btContinue.setOnClickListener {
            val action = AppointmentFragmentDirections.actionAppointmentFragmentToConfirmationFragment(
                calendar = calendar,
                selectedTime = selectedTime,
                selectedCar = selectedCar
            )
            findNavController().navigate(action)
        }






        binding.btAddCar.setOnClickListener {
            findNavController().navigate(AppointmentFragmentDirections.actionAppointmentFragmentToAddNewCarsFragment())
        }

        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar = "$year-$month-$dayOfMonth"
        }

        return binding.root
    }
    private fun setCardViewClickListener(cardView: CardView, selectedTime: String) {
        cardView.setOnClickListener {
            val currentColor = cardView.cardBackgroundColor.defaultColor
            val newColor = if (currentColor == resources.getColor(normalColor)) resources.getColor(clickedColor)
            else resources.getColor(normalColor)
            cardView.setCardBackgroundColor(newColor)

            // Update the selectedTime when a time slot is clicked
            this.selectedTime = selectedTime
        }
    }
}
