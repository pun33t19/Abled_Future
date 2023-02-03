package com.example.abledfuture.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.abledfuture.JobViewModel
import com.example.abledfuture.R
import com.example.abledfuture.databinding.FragmentJobDetailBinding


class JobDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel:JobViewModel by activityViewModels()
    private lateinit var binding:FragmentJobDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentJobDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.jobDataList.observe(viewLifecycleOwner){
            binding.descriptionText.text=it[0].job_description
        }

    }

}