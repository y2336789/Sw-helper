package com.bliss.csc.sw_helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private ArrayList<NoticeInfo> listData = new ArrayList<>(); //adapter에 들어갈 list
    private Context context;
    private Intent intent;


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();

        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킴
        // return 인자는 ViewHolder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_list, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
        // item을 하나씩 보여주는(bind 되는) 함수
        itemViewHolder.onBind(listData.get(i));
    }

    @Override
    public int getItemCount() {
        //RecyclerView의 총 개수
        return listData.size();
    }

    void addItem(NoticeInfo data) {
        // 외부에서 item을 추가시킬 함수
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder
    // 여기서 subView를 setting
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txt_chartTitle;
        private String url;
        private NoticeInfo data;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_chartTitle = itemView.findViewById(R.id.txt_chartTitle);
        }

        void onBind(NoticeInfo data){
            this.data = data;

            txt_chartTitle.setText(data.getNoTitle());

            itemView.setOnClickListener(this);
            txt_chartTitle.setOnClickListener(this);

            Log.d("RecyclerAdapter!!!!!", data.getNoTitle() + "\n");
        }

        @Override
        public void onClick(View view) {
            Log.d("click_item!!!!!", String.valueOf(view.getId()));
            Log.d("click_item!!!!!", data.getNoTitle()  + " / "+ data.getUrl());

            //상세 페이지 주소
            url = data.getUrl();

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://software.cbnu.ac.kr/"+url));
            view.getContext().startActivities(new Intent[]{intent});
        }
    }
}