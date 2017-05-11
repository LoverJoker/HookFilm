package com.example.joker.hookfilm;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import Adapter.Main_RecycleView_Adapter;
import Bean.Moviebean;
import Utils.EnterActivity;
import Utils.ZuixinJsoup_Utils;

/**
 * Created by Joker on 2017/1/19.
 */

public class zuixin_Fragment extends Base_Fragment implements ZuixinJsoup_Utils.OnDataRight{
    private RecyclerView Recycle_main ;
    private String TAG = "zuixin" ;
    @Override
    public void initAdapter() {
        ZuixinJsoup_Utils zuixinJsoup_utils = new ZuixinJsoup_Utils("http://www.ygdy8.com/html/gndy/dyzz/index.html",mRootActivity,Recycle_main);
        zuixinJsoup_utils.setOnDataRight(this);

    }

    @Override
    public void init(View view, RecyclerView Recycle_main) {
        this.Recycle_main = Recycle_main ;
    }

    @Override
    public void getData(ArrayList<Moviebean> moviebeens) {
        progress_main.setVisibility(View.INVISIBLE);
        Main_RecycleView_Adapter main_recycleView_adapter = new Main_RecycleView_Adapter(mRootActivity, moviebeens,Recycle_main,1,"");
        Log.d(TAG,moviebeens.get(1).getTitle());
        Recycle_main.setAdapter(main_recycleView_adapter);
        new EnterActivity().enterCurrent(main_recycleView_adapter,moviebeens,mRootActivity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
