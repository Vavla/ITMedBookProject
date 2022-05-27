package com.example.itmedbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

 class MedListAdapter extends  RecyclerView.Adapter<MedListAdapter.ViewHolder>{
     public LayoutInflater inflater;
     public List<Meddoc> meddocs;

     interface OnStateClickListener{
         void onStateClick(Meddoc meddoc, int position);
     }
     public   OnStateClickListener onClickListener;


    MedListAdapter(Context context, List<Meddoc> meddocs,OnStateClickListener onClickListener) {
        this.meddocs = meddocs;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override
    public MedListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.medbook1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meddoc meddoc = meddocs.get(position);
        holder.HospitalText.setText(meddoc.getHospital());
        holder.TypeRecordText.setText(meddoc.getTypeRecord());
        holder.AppealText.setText(meddoc.getAppeal());
        holder.DocText.setText(meddoc.getDoctor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onStateClick(meddoc, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meddocs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView HospitalText,TypeRecordText,AppealText,DocText;
        ViewHolder(View view){
            super(view);
             HospitalText = view.findViewById(R.id.HospitalText);
             TypeRecordText = view.findViewById(R.id.TypeRecordText);
             AppealText = view.findViewById(R.id.AppealText);
             DocText = view.findViewById(R.id.DocText);
        }
    }
}

