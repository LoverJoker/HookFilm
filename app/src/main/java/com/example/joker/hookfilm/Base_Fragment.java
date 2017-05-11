package com.example.joker.hookfilm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Joker on 2017/1/19.
 */

public abstract class Base_Fragment extends Fragment {

    public FragmentActivity mRootActivity;
    public View progress_main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootActivity = getActivity();
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        RecyclerView Recycle_main  = (RecyclerView) view.findViewById(R.id.Recycle_main);
        progress_main = view.findViewById(R.id.progress_main);
        Recycle_main.setLayoutManager(new GridLayoutManager(mRootActivity,3));
        init(view , Recycle_main);
        initAdapter();
        return view;
    }


    public abstract void init(View view ,  RecyclerView Recycle_main);

    public abstract void initAdapter();

}
