package com.example.abledfuture.fragments

import ViewPagerAdapter
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.abledfuture.databinding.FragmentHiringMainBinding

import android.speech.tts.TextToSpeech

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abledfuture.JobViewModel

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




        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(FirstFragment(), "First")
        adapter.addFragment(SecondFragment(), "Second")

        binding.viewPager.adapter = adapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)







    }


}