package com.example.abledfuture.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.abledfuture.ResumeBuilder
import com.example.abledfuture.databinding.FragmentJobReqDetailBinding


class JobReqDetailFragment : Fragment() {
    private val READ_REQUEST_CODE=1

    // TODO: Rename and change types of parameters
    private lateinit var binding:FragmentJobReqDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentJobReqDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upload.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent,READ_REQUEST_CODE)
        }

        binding.createResume.setOnClickListener {
            val intent=Intent(activity,ResumeBuilder::class.java)
            startActivity(intent)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode,resultData)
        var uri: Uri? = null
        if (requestCode === READ_REQUEST_CODE && resultCode === Activity.RESULT_OK) {

            if (resultData != null) {
                uri = resultData.getData()!!
                binding.upload.text=uri.toString()
                // Read the content of the file
                // ...
            }
        }
    }


}