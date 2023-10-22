package com.example.auto_moto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.auto_moto.databinding.FragmentTabLayoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabLayoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentTabLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabLayoutBinding.inflate(inflater, container, false)
        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(TabLayoutFragmentDirections.actionTabLayoutFragmentToHomeFragment())
        }

        // Initialize the ViewPager2 and TabLayout
        val viewPager: ViewPager2 = binding.viewPager
        val tab_Layout: TabLayout = binding.tabLayout

        val fragments: List<Fragment> = listOf(ExhaustsFragment(), FluidsFragment(), EnginesFragment(), BodyFragment(), TiresFragment(), SpoilersFragment())

        viewPager.adapter = MyViewPagerAdapter(requireActivity(), fragments)

        // Connect the TabLayout with the ViewPager2
        TabLayoutMediator(tab_Layout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Exhausts"
                1 -> "Fluids"
                2 -> "Engines"
                3 -> "Body"
                4 -> "Tires"
                5 -> "Spoilers"
                else -> null
            }
        }.attach()

        return binding.root
    }
}