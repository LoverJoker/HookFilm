package com.example.joker.hookfilm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import Adapter.Main_ViewPager_Adapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTablayout_main;
    private ViewPager mViewPager_main;
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private String[] titles;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initAcbar();
        findView();
        initData();
        init();
        initAdapter();
        initSearch();
    }

    private void initAcbar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initData() {
        titles = new String[]{"最新电影","国内电影","欧美电影","日韩电影"};
        fragments = new Fragment[]{new zuixin_Fragment() , new guonei_Fragment() ,
                                new oumei_Fragment() , new rihan_Fragment()};
    }

    private void initAdapter() {
        Main_ViewPager_Adapter main_viewPager_adapter = new Main_ViewPager_Adapter(this.getSupportFragmentManager(), titles, fragments);
        mViewPager_main.setAdapter(main_viewPager_adapter);
        mTablayout_main.setupWithViewPager(mViewPager_main);
    }

    private void init() {
//        this.setSupportActionBar(mToolbar);
        mTablayout_main.setTabGravity(TabLayout.GRAVITY_FILL);
        mTablayout_main.setTabMode(TabLayout.MODE_FIXED);
    }

    private void findView() {
        mViewPager_main = (ViewPager) findViewById(R.id.viewPager_main);
        mTablayout_main = (TabLayout) findViewById(R.id.tabLayout_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSearchView = (SearchView) findViewById(R.id.searchView);
    }

    private void initSearch() {
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query ==""|| query.isEmpty()){
                    Toast.makeText(MainActivity.this,"请输入电影关键字",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, search_Activity.class);
                    intent.putExtra("value",query);
                    startActivity(intent);
                    mSearchView.clearFocus();  //可以收起键盘
                    mSearchView.onActionViewCollapsed();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
