package com.example.abledfuture;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.abledfuture.fragments.BlogsFragment;

public class PostContent extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView backgroundimg;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String desc = extras.getString("desc");
        String img = extras.getString("img");

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        description =(TextView) findViewById(R.id.description);
        description.setText(desc);

        backgroundimg=findViewById(R.id.postImage);
        Glide.with(getApplicationContext()).load(img).into(backgroundimg);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}