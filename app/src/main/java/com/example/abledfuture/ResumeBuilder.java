package com.example.abledfuture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Locale;

public class ResumeBuilder extends AppCompatActivity {
    private SpeechRecognizer speechRecognizer;
    private TextToSpeech tts;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private int currentField = 0;
    private String resume;
    TextInputEditText experience ,tell,strengths;
    private TextInputEditText[] fields = {experience, tell, strengths};
    String e,t,s;
    private String[] fieldsName = {"experience", "tell", "strengths"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_builder);
        experience = findViewById(R.id.Experience);
        tell = findViewById(R.id.Tell);
        strengths = findViewById(R.id.strength);
//
//        fields[0] = findViewById(R.id.Experience);
//        fields[1] = findViewById(R.id.Tell);
//        fields[2] = findViewById(R.id.strength);
//        strengths.setText("ABC");
//

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
                processResult(result);
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
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        } else {
            // Start recording

           startListening();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    speechRecognizer.stopListening();
                }
            }, 15000);
        }


    }

    private void startListening() {
        speak("Please speak about your "+fieldsName[currentField]+" in 10 seconds");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");
      speechRecognizer.startListening(getIntent());
    }

    private void speak(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    private void nextField() {
        if (currentField >= fields.length - 1) {
            // All fields have been filled
           Intent i = new Intent(this,Resume.class);
            String resume = "EXPERIENCE\n" + e + "\n\n" +
                    "STRENGTHS\n" + s + "\n\n" +
                    "ABOUT\n" + t;
           i.putExtra("e",e);
            i.putExtra("s",s);
            i.putExtra("t",t);

            startActivity(i);
//            return;
        }

        currentField++;
//        speak("Please tell me more aboout yourself in 10 seconds");
        speechRecognizer.startListening(getIntent());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                speechRecognizer.stopListening();
            }
        }, 15000);
    }
    private void processResult(List<String> results) {
        // Store the input for the current field
        String fieldInput = results.get(0);
//        speak("You said " + fieldInput);
//          fields[currentField].setText(fieldInput);
//
        Log.d("TEST",currentField+" "+fieldInput);


        if(currentField==0)
        {
            experience.setText(fieldInput);
          e = fieldInput;
        }
        else if(currentField==1)
        {
            tell = findViewById(R.id.Tell);
            t = fieldInput;
            tell.setText(fieldInput);
        }
        else {
            s = fieldInput;
            strengths = findViewById(R.id.strength);
            strengths.setText(fieldInput);
        }

        // Move to the next field
        nextField();
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