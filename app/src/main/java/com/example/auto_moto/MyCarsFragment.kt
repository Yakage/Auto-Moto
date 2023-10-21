package com.example.auto_moto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.auto_moto.databinding.FragmentMyCarsBinding
import com.example.auto_motov04.DBhelper

class MyCarsFragment : Fragment() {
    private lateinit var binding: FragmentMyCarsBinding
    private lateinit var myCarsAdapter: MyCarsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var db: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = DBhelper(requireContext())
        binding = FragmentMyCarsBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(MyCarsFragmentDirections.actionMyCarsFragmentToAccountFragment())
        }
        binding.btAddNewCar.setOnClickListener {
            findNavController().navigate(MyCarsFragmentDirections.actionMyCarsFragmentToAddNewCarsFragment())
        }

        recyclerView = binding.myCarList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        myCarsAdapter = MyCarsAdapter(ArrayList(),this) // Pass a reference to the MyCarsFragment
        recyclerView.adapter = myCarsAdapter

        // Fetch and populate user's car data
        fetchUserCars()


    }

    private fun fetchUserCars() {
        val userCars = db.getUserCars()
        myCarsAdapter.updateData(userCars)
    }


    fun deleteCar(carName: String, carModel: String, carBrand: String) {
        val isCarDeleted = db.deleteCar(carName, carModel, carBrand)

        if (isCarDeleted) {
            Toast.makeText(requireContext(), "Car Deleted: $carName $carModel $carBrand", Toast.LENGTH_SHORT).show()
            // Reload the user's car data after deletion
            fetchUserCars()
        } else {
            Toast.makeText(requireContext(), "Failed to delete car", Toast.LENGTH_SHORT).show()
        }
    }
}
