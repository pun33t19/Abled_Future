package com.example.abledfuture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.abledfuture.databinding.ActivityMainBinding;
import com.example.abledfuture.fragments.MeetingFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    float x1,x2,y1,y2;
    private GestureDetectorCompat mDetector;

    ImageView fragImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mDetector.onTouchEvent(event);
////        System.out.println("HELLO");
//        MeetingFragment fragment = new MeetingFragment();
//        Instructions previousFragment = new Instructions();
//        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
//        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
//        transaction.remove(previousFragment);
//        transaction.commit();
//        return super.onTouchEvent(event);
//    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        MeetingFragment fragment = new MeetingFragment();
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
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view,fragment).commit();
            }else if(x1 >= x2){
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, fragment).commit();
            }
            break;
        }
        return false;
    }


}