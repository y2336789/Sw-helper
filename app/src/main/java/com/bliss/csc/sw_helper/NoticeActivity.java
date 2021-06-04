package com.bliss.csc.sw_helper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    String sw_url = "https://software.cbnu.ac.kr/bbs/bbs.php?db=notice";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        recyclerView = findViewById(R.id.recyclerview_notice);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData(){
        SwJsoup jsoupAsyncTask = new SwJsoup();
        jsoupAsyncTask.execute();
    }

    private class SwJsoup extends AsyncTask<Void, Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> listUrl = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(sw_url).get();
                final Elements rank_list1 = doc.select("td.body_bold nobr a");

                Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //순위정보
                        for(Element element: rank_list1) {
                            listTitle.add(element.text());
                        }

                        for (Element element : rank_list1) {
                            // 상세 주소만 나올 수 있도록 문자열 추출 작업
                            String tmp = element.attr("href");
                            listUrl.add(tmp);
                        }

                        for (int i = 0; i < 25 ; i++) {
                            NoticeInfo data = new NoticeInfo();
                            data.setNoTitle(listTitle.get(i));
                            data.setUrl(listUrl.get(i));
                            adapter.addItem(data);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}