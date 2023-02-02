package com.example.abledfuture;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.abledfuture.fragments.MeetingFragment;

class MyGestureControl extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 200;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            float diffAbs = Math.abs(e1.getY() - e2.getY());
            float velocityAbs = Math.abs(velocityY);
            if (diffAbs > SWIPE_MAX_OFF_PATH)
                return false;
            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && velocityAbs > SWIPE_THRESHOLD_VELOCITY) {
                // upward swipe
                System.out.println("Hello");
                return true;
            }
        } catch (Exception e) {
            Log.e("MyGestureDetector", "Error onFling: " + e.getMessage());
        }
        return false;
    }

    private static void startNewActivity() {
        MeetingFragment fragment = new MeetingFragment();

    }
}