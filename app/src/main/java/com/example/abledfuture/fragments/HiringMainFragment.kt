package com.example.abledfuture.fragments

import ViewPagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.abledfuture.databinding.FragmentHiringMainBinding


class HiringMainFragment : Fragment() {

    private lateinit var binding: FragmentHiringMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHiringMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(FirstFragment(), "First")
        adapter.addFragment(SecondFragment(), "Second")

        binding.viewPager.adapter = adapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)







    }


}