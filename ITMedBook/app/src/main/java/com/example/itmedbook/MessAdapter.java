package com.example.itmedbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.ViewHolder> {
    public List<Messanges> messanges = new ArrayList<>();
    public LayoutInflater inflater ;
    interface OnStateClickListener{
       void onStateClick(Messanges messange,int position);
    }
    public MessAdapter.OnStateClickListener OnStateListener;
    MessAdapter(Context context, List<Messanges>messanges, MessAdapter.OnStateClickListener OnStateListener){
        this.inflater  = LayoutInflater.from(context);
        this.messanges = messanges;
        this.OnStateListener = OnStateListener;
    }

    @Override
    public MessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.chat_message, parent, false);
        return new MessAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessAdapter.ViewHolder holder, int position) {
        Messanges messange = messanges.get(position);
        holder.FromTo.setText(messange.getFromTo() + "   " + (messange.getTime()));
        holder.MessText.setText(messange.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnStateListener.onStateClick(messange, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return messanges.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView FromTo,MessText;
        ViewHolder(View view){
            super(view);
            FromTo = view.findViewById(R.id.FromTo);
            MessText = view.findViewById(R.id.MessText);
        }
    }
}
