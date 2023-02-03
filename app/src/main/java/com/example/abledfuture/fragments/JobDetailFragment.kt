package com.example.abledfuture.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.abledfuture.JobViewModel
import com.example.abledfuture.R
import com.example.abledfuture.databinding.FragmentJobDetailBinding


class JobDetailFragment : Fragment() {

    private val viewModel: JobViewModel by activityViewModels()
    private lateinit var binding: FragmentJobDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args=arguments
        val pos=args!!.getInt("position")



        viewModel.jobDataList.observe(viewLifecycleOwner) {
            binding.descriptionText.text = it[pos].job_description
            Glide.with(view).load(it[pos].employer_logo).into(binding.employerLogo)
            binding.jobPositionText.text=it[pos].job_title
        }

        binding.applyButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view,JobReqDetailFragment(),"TAG")
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit()
        }

    }

}