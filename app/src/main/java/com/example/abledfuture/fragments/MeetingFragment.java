package com.example.abledfuture.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Models.MeetModel;
import com.example.abledfuture.Adapters.MeetAdapter;
import com.example.abledfuture.R;
import com.example.abledfuture.databinding.FragmentMeetingBinding;

import java.util.ArrayList;

public class MeetingFragment extends Fragment {

    public MeetingFragment() {
        // Required empty public constructor
    }

    ArrayList<MeetModel> list;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();
        list.add(new MeetModel("Content writing","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eget sodales sem."));
        list.add(new MeetModel("Seminar","Cras fermentum mi est, eu pellentesque elit pellentesque sit amet. "));
        list.add(new MeetModel("Content writing","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eget sodales sem."));
        list.add(new MeetModel("Video Editing","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eget sodales sem."));
        list.add(new MeetModel("Content writing","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eget sodales sem."));
        MeetAdapter adapter = new MeetAdapter(list,binding.recyclerViewMeeting,getContext());
        binding.recyclerViewMeeting.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewMeeting.setAdapter(adapter);
    }

    FragmentMeetingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeetingBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }
}