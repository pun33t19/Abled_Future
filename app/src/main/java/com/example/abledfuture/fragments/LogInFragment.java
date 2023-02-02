package com.example.abledfuture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.abledfuture.AuthService;
import com.example.abledfuture.Instructions;
import com.example.abledfuture.R;
import com.example.abledfuture.databinding.FragmentLogInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class LogInFragment extends Fragment {

    private FragmentLogInBinding binding;
    float x1,x2,y1,y2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_log_in, container, false);
        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    //do something
                    onTouchEvent(event);
                }
                return true;
            }
        });
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText=binding.emailId.getText().toString();
                String passwordText=binding.password.getText().toString();

                if(emailText.isEmpty() || passwordText.isEmpty())
                    Toast.makeText(getContext(), "Please enter the empty fields", Toast.LENGTH_SHORT).show();
                if(passwordText.length()<6)
                   Toast.makeText(getContext(), "Password must be greater than 6 characters", Toast.LENGTH_SHORT).show();
                AuthService authService=new AuthService(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance());


                authService.signInUser(emailText,passwordText,getContext());
            }
        });

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

//                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new Instructions()).commit();
                }
                break;
        }
        return false;
    }
}