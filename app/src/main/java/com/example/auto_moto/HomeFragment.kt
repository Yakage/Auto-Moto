package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    private lateinit var adapter: Adapter
    private var recyclerView: RecyclerView? = null
    private lateinit var carArray: ArrayList<CarItems>

    lateinit var imgID: Array<Int>
    lateinit var heading: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareCarList()
        val layout = LinearLayoutManager(context)
        recyclerView?.layoutManager = layout
        recyclerView = view.findViewById(R.id.rvCarLists)
        recyclerView?.setHasFixedSize(true)
        adapter = Adapter(carArray)
        recyclerView?.adapter = adapter

    }
    private fun prepareCarList(){
        carArray = arrayListOf<CarItems>()

        imgID = arrayOf(
            R.drawable.auto_moto,
            R.drawable.icon_red_car,
            R.drawable.icon_home,
            R.drawable.icon_account,
            R.drawable.icon_red_car,
            R.drawable.auto_moto
        )

        heading = arrayOf(
            getString(R.string.app_name),
            getString(R.string.open_nav),
            getString(R.string.open_nav),
            getString(R.string.app_name),
            getString(R.string.app_name),
            getString(R.string.app_name)
        )

        for(i in imgID.indices){
            val imgId = CarItems(imgID[i],heading[i])
            carArray.add (imgId)

        }
    }
}