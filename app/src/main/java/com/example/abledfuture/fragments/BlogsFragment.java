package com.example.abledfuture.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.PostModel;
import com.example.abledfuture.Adapters.PostAdapter;
import com.example.abledfuture.CreatePost;
import com.example.abledfuture.Instructions;
import com.example.abledfuture.PostContent;
import com.example.abledfuture.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlogsFragment extends Fragment implements PostAdapter.onClickListner {

    FloatingActionButton createPost;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<PostModel> postModelList;
    FirebaseAuth auth;
    ProgressDialog pd;
    ProgressBar progressBar;
    float x1,x2,y1,y2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_blogs, container, false);

//        progressBar = v.findViewById(R.id.progressBar_blog);

//        Intent intent = new Intent(getActivity(), PostContent.class);
//        startActivity(intent);

        createPost = (FloatingActionButton) v.findViewById(R.id.createBlog);


        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePost.class);
                startActivity(intent);
            }
        });
        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    //do something
                    onTouchEvent(event);
                }
                return true;
            }
        });

        return v;
    }

    private void loadPosts() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                postModelList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    PostModel postModel = ds.getValue(PostModel.class);
                    postModelList.add(postModel);
                    postAdapter = new PostAdapter(getContext(),postModelList,BlogsFragment.this::onClicked );
                    recyclerView.setAdapter(postAdapter);
                    postAdapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        // this will load from end and will show latest post first
        recyclerView.setLayoutManager(layoutManager);

        postModelList = new ArrayList<>();
        loadPosts();

    }

    @Override
    public void onClicked(int position) {

        Intent intent = new Intent(getActivity(),PostContent.class);
        intent.putExtra("title",postModelList.get(position).getpTitle());
        intent.putExtra("desc",postModelList.get(position).getpDescription());
        intent.putExtra("img",postModelList.get(position).getpImage());
        startActivity(intent);
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