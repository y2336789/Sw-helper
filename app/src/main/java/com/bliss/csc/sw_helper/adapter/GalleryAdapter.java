package com.bliss.csc.sw_helper.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bliss.csc.sw_helper.R;

import java.util.ArrayList;

public class GalleryAdapter  extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    private String[] mDataset;

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public GalleryViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public GalleryAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public GalleryAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        GalleryViewHolder vh = new GalleryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        TextView textVIew = holder.cardView.findViewById(R.id.textView2);
        textVIew.setText(mDataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}