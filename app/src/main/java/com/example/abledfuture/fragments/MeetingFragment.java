package com.example.abledfuture.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.Models.MeetModel;
import com.example.abledfuture.Adapters.MeetAdapter;
import com.example.abledfuture.Instructions;
import com.example.abledfuture.R;
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
    float x1,x2,y1,y2;
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

        /*binding.scheduleMeetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MeetActivity.class));
            }
        });*/

    }

    FragmentMeetingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeetingBinding.inflate(getLayoutInflater());
        View v =  inflater.inflate(R.layout.fragment_meeting, container, false);

        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    //do something
//                    onTouchEvent(event);
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            x1 = event.getX();
                            y1 = event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            x2 = event.getX();
                            y2 = event.getY();
                            if(x1 < x2){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,meet).commit();
//                    FragmentTransaction fr = getFragmentManager().beginTransaction();
//                    fr.replace(R.id.fragment_container_view,new Instructions());
//                    fr.commit();
                            }else if(x1 >= x2){

                                getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new Instructions()).commit();
                            }
                            break;
                    }
                    return false;
                }
                return true;
            }
        });

        return binding.getRoot();
    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        MeetingFragment meet = new MeetingFragment();
        BlogsFragment blogs = new BlogsFragment();
//        Instructions previousFragment = new Instructions();
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,meet).commit();
//                    FragmentTransaction fr = getFragmentManager().beginTransaction();
//                    fr.replace(R.id.fragment_container_view,new Instructions());
//                    fr.commit();
                }else if(x1 >= x2){

                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new Instructions()).commit();
                }
                break;
        }
        return false;
    }
}