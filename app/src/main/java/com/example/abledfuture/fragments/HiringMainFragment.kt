package com.example.abledfuture.fragments

import JobHiringAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abledfuture.JobViewModel
import com.example.abledfuture.databinding.FragmentHiringMainBinding
import com.example.abledfuture.model.JobDataModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class HiringMainFragment : Fragment() {

    private lateinit var binding: FragmentHiringMainBinding
    private val viewModel: JobViewModel by viewModels()

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
        val list: MutableList<JobDataModel> = mutableListOf()



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
            binding.recyclerViewJob.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            binding.recyclerViewJob.adapter=JobHiringAdapter(list)


        }

        Log.d("LIST_NOW_HERE",list.toString())




    }


}