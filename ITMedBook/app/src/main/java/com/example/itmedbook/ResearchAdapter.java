package com.example.itmedbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.ViewHolder>{
    public LayoutInflater inflater;
    public List<Researches> researches;

    interface OnStateClickListener{
        void onStateClick(Researches research, int position);
    }
    public ResearchAdapter.OnStateClickListener onClickListener;


    ResearchAdapter(Context context, List<Researches> researches, ResearchAdapter.OnStateClickListener onClickListener) {
        this.researches = researches;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override
    public ResearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.research, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResearchAdapter.ViewHolder holder, int position) {
        Researches research = researches.get(position);
        holder.TitleRes.setText(research.getTitleRes());
        holder.ResultRes.setText(research.getResultRes());
        holder.DocRes.setText(research.getDoctor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onStateClick(research, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return researches.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView TitleRes,ResultRes,DocRes;
        ViewHolder(View view){
            super(view);
            TitleRes = view.findViewById(R.id.TitleRes);
            ResultRes = view.findViewById(R.id.ResultRes);
            DocRes = view.findViewById(R.id.DocRes);
        }
    }
}


