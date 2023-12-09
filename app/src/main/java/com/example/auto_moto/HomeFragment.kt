package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.auto_moto.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: Adapter
    private var recyclerView: RecyclerView? = null
    private lateinit var carArray: ArrayList<CarItem>
    lateinit var imgID: Array<Int>
    lateinit var heading: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        binding.tvViewAllForServices.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToServicesFragment())
        }
        binding.cardViewExhaust.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTabLayoutFragment())
        }
        binding.cardViewFluid.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTabLayoutFragment())
        }
        binding.cardViewEngines.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTabLayoutFragment())
        }
        binding.cardViewBody.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTabLayoutFragment())
        }
        binding.cardViewTires.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTabLayoutFragment())
        }
        binding.cardViewSpoilers.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTabLayoutFragment())
        }
        binding.tvViewAllFeaturedItems.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTabLayoutFragment())
        }


        return binding.root
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
        carArray = arrayListOf<CarItem>()
        imgID = arrayOf(
            R.drawable.auto_moto,
            R.drawable.auto_moto,
            R.drawable.auto_moto,
            R.drawable.auto_moto,
            R.drawable.auto_moto,
            R.drawable.auto_moto
        )

        heading = arrayOf(
            "Item 1 demo",
            "Item 2 demo",
            "Item 3 demo,",
            "Item 4 demo",
            "Item 5 demo",
            "Item 6 demo"
        )

        for(i in imgID.indices){
            val imgId = CarItem(imgID[i],heading[i])
            carArray.add (imgId)

        }
    }
}