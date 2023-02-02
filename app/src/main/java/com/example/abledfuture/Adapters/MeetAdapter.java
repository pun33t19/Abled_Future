package com.example.abledfuture.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.MeetModel;
import com.example.abledfuture.R;

import java.util.List;

public class MeetAdapter extends RecyclerView.Adapter<MeetAdapter.Holder> {

    private List<MeetModel> list;
    private RecyclerView recyclerView;
    private Context context;
    private String LogTag = MeetAdapter.class.getSimpleName();

    public MeetAdapter(List<MeetModel> list, RecyclerView recyclerView, Context context) {
        this.list = list;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meet_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        holder.topic.setText(list.get(position).getTopic());
        holder.description.setText(list.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView topic;
        TextView description;

        public Holder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.meet_topic);
            description = itemView.findViewById(R.id.description);

        }


    }


}