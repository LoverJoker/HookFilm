package com.example.joker.hookfilm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import Adapter.search_RecAdapter;
import Bean.Moviebean;
import Utils.SearchJsoup_Utils;


/**
 * Created by Joker on 2016/12/28.
 */

public class search_Activity extends AppCompatActivity implements SearchJsoup_Utils.OnDataRight  {

    private Toolbar mToolbar;
    private RecyclerView mSearch_rec;
    private View mProgress_search;

    private ImageView mSearch_back;
    private TextView mSearch_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findView();
        initData();
        init();
    }

    private void initData() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("value");
        mSearch_title.setText(value);
        netsearch(value);
    }

    private void netsearch(String value) {
        try {
            String valuegbk = URLEncoder.encode(value, "gbk");
            String url = "http://s.dydytt.net/plus/search.php?kwtype=0&searchtype=title&keyword="+valuegbk;
            SearchJsoup_Utils searchJsoup_utils = new SearchJsoup_Utils(url, search_Activity.this, mSearch_rec);
            searchJsoup_utils.setOnDataRight(this);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        mSearch_rec.setLayoutManager(new LinearLayoutManager(this));
        mSearch_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_Activity.this.finish();
            }
        });
    }

    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_search);
        mSearch_rec = (RecyclerView) findViewById(R.id.search_rec);
        mProgress_search = findViewById(R.id.progress_search);
        mSearch_back = (ImageView) findViewById(R.id.search_back);
        mSearch_title = (TextView) findViewById(R.id.search_title);
    }

    @Override
    public void getData(final ArrayList<Moviebean> moviebeens) {
        mProgress_search.setVisibility(View.INVISIBLE);
        search_RecAdapter search_recAdapter = new search_RecAdapter(this, moviebeens);
        Log.d("zuixin",moviebeens.isEmpty()+"");
        mSearch_rec.setAdapter(search_recAdapter);
        search_recAdapter.setOnitemClick(new search_RecAdapter.OnitemClick() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(search_Activity.this, current_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("moviebeens",moviebeens);
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
