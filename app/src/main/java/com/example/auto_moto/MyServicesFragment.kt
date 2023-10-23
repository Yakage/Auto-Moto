package com.example.auto_moto


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.auto_moto.databinding.FragmentMyServicesBinding
import com.example.auto_motov04.DBhelper

class MyServicesFragment : Fragment() {
    private lateinit var binding: FragmentMyServicesBinding
    private lateinit var db: DBhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyServicesBinding.inflate(inflater,container, false)
        db = DBhelper(requireContext())
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_services, container, false)



    }
}