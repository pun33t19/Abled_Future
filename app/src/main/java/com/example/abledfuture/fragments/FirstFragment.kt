package com.example.abledfuture.fragments

import JobHiringAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abledfuture.JobViewModel
import com.example.abledfuture.R
import com.example.abledfuture.databinding.FragmentFirstBinding
import com.example.abledfuture.model.JobDataModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding:FragmentFirstBinding
    private val viewModel: JobViewModel by activityViewModels()
    private val list: MutableList<JobDataModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        Firebase.firestore.collection("Jobs").get().addOnSuccessListener { result ->
            for (document in result) {
                val model = JobDataModel(
                    document.data["EmployerName"].toString(),
                    document.data["EmployerLogo"].toString(),
                    document.data["JobPublisher"].toString(),
                    document.data["JobEmploymentType"].toString(),
                    document.data["JobTitle"].toString(),
                    document.data["JobDescription"].toString(),
                    document.data["JobCity"].toString(),
                    document.data["JobState"].toString(),
                    document.data["JobSalary"].toString(),
                    document.data["JobPreference"].toString(),
                )

                list.add(model)

            }
            Log.d("LIST",list.toString())
            binding.recyclerViewJob.layoutManager=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            binding.recyclerViewJob.adapter=JobHiringAdapter(list,requireContext())
            viewModel.jobDataList.value=list

        }

//        viewModel.jobDataList.observe(viewLifecycleOwner){
//            binding.recyclerViewJob.layoutManager=
//                LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//            binding.recyclerViewJob.adapter=JobHiringAdapter(list,requireContext())
//
//        }



        Log.d("LIST_NOW_HERE",list.toString())

    }

    override fun onResume() {
        super.onResume()

        viewModel.jobDataList.observe(viewLifecycleOwner){
            binding.recyclerViewJob.layoutManager=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            binding.recyclerViewJob.adapter=JobHiringAdapter(list,requireContext())

        }

    }
}