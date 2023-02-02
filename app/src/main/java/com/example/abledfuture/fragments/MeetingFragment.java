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
import com.example.abledfuture.databinding.FragmentMeetingBinding;

//import org.jitsi.meet.sdk.JitsiMeet;
//import org.jitsi.meet.sdk.JitsiMeetActivity;
//import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
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

//        URL server;
//        try{
//            server =new URL("https://meet.jit.si");
//            JitsiMeetConferenceOptions defaultOptions=
//                    new JitsiMeetConferenceOptions.Builder()
//                            .setServerURL(server)
//                            .setFeatureFlag("welcomepage.enabled", false).build();
//            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
//
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//        binding.scheduleMeetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
//                        .setRoom("ksjijijsddk")
//                        .setFeatureFlag("welcomepage.enabled", false).build();
//                JitsiMeetActivity.launch(getContext(),options);
//            }
//        });
//
//        /*binding.scheduleMeetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), MeetActivity.class));
//            }
//        });*/

    }

    FragmentMeetingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeetingBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }
}