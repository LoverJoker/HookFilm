package com.example.joker.hookfilm;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import Adapter.Main_RecycleView_Adapter;
import Bean.Moviebean;
import Utils.EnterActivity;
import Utils.guonei_JsoupUtils;

/**
 * Created by Joker on 2017/1/19.
 */

public class guonei_Fragment extends Base_Fragment implements guonei_JsoupUtils.OnDataRight {

    private View view;
    private RecyclerView mRecycle_main  ;



    @Override
    public void init(View view , RecyclerView Recycle_main) {
        this.view = view ;
        mRecycle_main = Recycle_main ;
    }

    @Override
    public void initAdapter() {
        guonei_JsoupUtils guonei_jsoupUtils = new guonei_JsoupUtils("http://www.ygdy8.com/html/gndy/china/index.html", mRootActivity, mRecycle_main);
        guonei_jsoupUtils.setOnDataRight(this);
    }

    @Override
    public void getData(ArrayList<Moviebean> moviebeens) {
        String NextUrl = "http://www.ygdy8.com/html/gndy/china/list_4_";
        progress_main.setVisibility(View.INVISIBLE);
        Main_RecycleView_Adapter main_recycleView_adapter = new Main_RecycleView_Adapter(mRootActivity, moviebeens,mRecycle_main,2 , NextUrl);
        mRecycle_main.setAdapter(main_recycleView_adapter);
        new EnterActivity().enterCurrent(main_recycleView_adapter,moviebeens,mRootActivity);
    }
}
