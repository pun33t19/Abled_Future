package com.example.abledfuture.fragments

import JobHiringAdapter
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abledfuture.JobViewModel
import com.example.abledfuture.databinding.FragmentHiringMainBinding
import com.example.abledfuture.model.JobDataModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


class HiringMainFragment : Fragment() {

    private lateinit var binding: FragmentHiringMainBinding
    private val viewModel: JobViewModel by activityViewModels()
    private var tts: TextToSpeech? = null
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
    private fun speak(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list: MutableList<JobDataModel> = mutableListOf()
        tts = TextToSpeech(context) { arg0 ->
            if (arg0 == TextToSpeech.SUCCESS) {
                tts?.setLanguage(Locale.US)
                speak("You have entered the Hiring Section")
            }
        }


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
            binding.recyclerViewJob.adapter=JobHiringAdapter(list,requireContext())
            viewModel.jobDataList.value=list

        }



        Log.d("LIST_NOW_HERE",list.toString())




    }


}