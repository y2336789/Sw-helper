package com.bliss.csc.sw_helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private ArrayList<ItemObject> mList;

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_title = (TextView) itemView.findViewById(R.id.titleTextView);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                    }
                }
            });
        }
    }

    public MyAdapter(ArrayList<ItemObject> list){
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.textView_title.setText(String.valueOf(mList.get(position).getTitle()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
