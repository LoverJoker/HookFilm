package com.example.joker.hookfilm;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import Adapter.current_RecAdapter;
import Bean.Moviebean;
import Utils.Glide_Utils;

/**
 * Created by Joker on 2016/12/19.
 */

public class current_Activity extends AppCompatActivity {

    private Toolbar mCurrent_toolbar;
    private RecyclerView mCurrent_rec;
    private Object data;
    private String title;
    private ImageView current_img;
    private String currentimgurl;
    private String currentinfo;
    private RecyclerView current_recyclerView;
    private FloatingActionButton mCurrent_fab;
    private String dowlink;
    private int position;
    private ArrayList<Moviebean> moviebeens;
    private ActionBar acbar;
    private CoordinatorLayout mCoordinator_current;
    private AppBarLayout mCurrent_appbar;
    private CollapsingToolbarLayout mCurrent_collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        acbar = getSupportActionBar();
        initAcbar();
        getData();
        findView();
        init();

    }

    private void initAcbar() {
        setSupportActionBar(mCurrent_toolbar);
        if (acbar!=null){
            acbar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void findView() {
        mCurrent_toolbar = (Toolbar) findViewById(R.id.current_toolbar);
        mCurrent_rec = (RecyclerView) findViewById(R.id.current_recyclerView);
        current_img = (ImageView) findViewById(R.id.current_img);
        mCurrent_fab = (FloatingActionButton) findViewById(R.id.current_fab);
        mCoordinator_current = (CoordinatorLayout) findViewById(R.id.coordinator_current);
        mCurrent_appbar = (AppBarLayout) findViewById(R.id.current_appbar);
        mCurrent_collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.current_collapsingToolbarLayout);
    }

    private void init() {
        String[] infosplite = moviebeens.get(position).getCurrentinfo().split("◎");
//        mCurrent_toolbar.setTitle(moviebeens.get(position).getTitle());
        mCurrent_collapsingToolbarLayout.setTitle(moviebeens.get(position).getTitle());
        mCurrent_rec.setLayoutManager(new LinearLayoutManager(this));
        mCurrent_rec.setAdapter(new current_RecAdapter(this ,infosplite));
        Glide_Utils.SetGlide(this,moviebeens.get(position).getImgurl(),current_img);
        mCurrent_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }


    public void getData() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        moviebeens = (ArrayList<Moviebean>) intent.getSerializableExtra("moviebeens");
        dowlink = moviebeens.get(0).getDowlink();


    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("下载地址,在线播放请安装迅雷")
//                .setSingleChoiceItems(moviebeens.get(position).getDowlinks(), 0,null)
                .setMessage(moviebeens.get(position).getDowlink())
                .setPositiveButton("调用迅雷下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(moviebeens.get(position).getDowlink()));
                        intent.addCategory("android.intent.category.DEFAULT");
                        if(intent==null){
                            Snackbar.make(mCurrent_fab,"播放,请先下载安装迅雷",Snackbar.LENGTH_LONG)
                                    .setAction("点击下载迅雷", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(Intent.ACTION_VIEW,
                                                    Uri.parse("http://m.down.sandai.net/MobileThunder/Android_5.32.2.4620/XLWXguanwang.apk")));
                                        }
                                    });
                        }else {

                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
