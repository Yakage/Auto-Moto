package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.auto_moto.databinding.FragmentMyCarsBinding

class MyCarsFragment : Fragment() {
    private lateinit var binding: FragmentMyCarsBinding
    private lateinit var myCarsAdapter: MyCarsAdapter
    private var recyclerView: RecyclerView? = null
    private lateinit var myCars: ArrayList<MyCarList>
    lateinit var imageID: Array<Int>
    lateinit var name: Array<String>
    lateinit var model: Array<String>
    lateinit var number: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyCarsBinding.inflate(inflater, container, false)
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(MyCarsFragmentDirections.actionMyCarsFragmentToAccountFragment())
        }
        binding.btAddNewCar.setOnClickListener {
            findNavController().navigate(MyCarsFragmentDirections.actionMyCarsFragmentToAddNewCarsFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareMyCarList()
        val layout = LinearLayoutManager(context)
        recyclerView?.layoutManager = layout
        recyclerView = view.findViewById(R.id.myCarList)
        recyclerView?.setHasFixedSize(true)
        myCarsAdapter = MyCarsAdapter(myCars)
        recyclerView?.adapter = myCarsAdapter
    }

    private fun prepareMyCarList(){
        myCars = arrayListOf<MyCarList>()

        imageID = arrayOf(
            R.drawable.icon_red_car,
            R.drawable.icon_red_car,
            R.drawable.icon_red_car,
            R.drawable.icon_red_car
        )

        name = arrayOf(
            "Tesla",
            "Tesla",
            "Tesla",
            "Tesla"
        )
        model = arrayOf(
            "Model X",
            "Model X",
            "Model X",
            "Model X"
        )
        number = arrayOf(
            "2023",
            "2023",
            "2023",
            "2023"
        )


        for(i in imageID.indices){
            val imgId = MyCarList(imageID[i],name[i],model[i],number[i])
            myCars.add (imgId)
        }
    }

}