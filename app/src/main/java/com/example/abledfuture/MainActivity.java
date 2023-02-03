package com.example.abledfuture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;


import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abledfuture.databinding.ActivityMainBinding;
import com.example.abledfuture.fragments.BlogsFragment;
import com.example.abledfuture.fragments.HiringMainFragment;
import com.example.abledfuture.fragments.JobDetailFragment;
import com.example.abledfuture.fragments.LogInFragment;
import com.example.abledfuture.fragments.MeetingFragment;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    ActivityMainBinding binding;
    float x1,x2,y1,y2;
    private GestureDetectorCompat gestureDetector;
    private TextToSpeech textToSpeech;
    private SpeechRecognizer speechRecognizer;
    FragmentContainerView fr ;
    private TextToSpeech tts;
    ImageView fragImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fr = findViewById(R.id.fragment_container_view);
        gestureDetector = new GestureDetectorCompat(this, this);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) { }

            @Override
            public void onBeginningOfSpeech() { }

            @Override
            public void onRmsChanged(float rmsdB) { }

            @Override
            public void onBufferReceived(byte[] buffer) { }

            @Override
            public void onEndOfSpeech() { }

            @Override
            public void onError(int error) { }

            @Override
            public void onResults(Bundle results) {
                List<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String text = result.get(0);
                Log.d("SPEECH",text);
//                fields[currentField].setText(text);
//                experience.setText(text);
//                processResult(result);
//                speak(text);
//
//                nextField();
            }

            @Override
            public void onPartialResults(Bundle partialResults) { }

            @Override
            public void onEvent(int eventType, Bundle params) { }
        });
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
            }
        });
//      speak();


    }
    private void speak(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, fragment);
        transaction.setReorderingAllowed(false);
        transaction.addToBackStack("hELLO");
        transaction.commit();
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaX = e2.getX() - e1.getX();
        float deltaY = e2.getY() - e1.getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // Horizontal swipe
            if (deltaX > 0) {
                // Swipe right
                replaceFragment(new HiringMainFragment());
            } else {
                // Swipe left
                replaceFragment(new MeetingFragment());
            }
        } else {
            // Vertical swipe
            if (deltaY > 0) {
                // Swipe down
                replaceFragment(new BlogsFragment());
            } else {
                // Swipe up
                replaceFragment(new JobDetailFragment());
            }
        }
        return true;
    }




//    @Override
//    public void onBackPressed() {
//
//        super.onBackPressed();
//        finishAffinity();
//    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    @Override
    protected void onDestroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


}