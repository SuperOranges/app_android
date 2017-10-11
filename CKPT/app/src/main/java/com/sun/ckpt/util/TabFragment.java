package com.sun.ckpt.util;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sun.ckpt.MainActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */

public class TabFragment extends Fragment {
    private List<String> mTitle ;

    public TabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            mTitle = getArguments().getStringArrayList("title");
        }

        RecyclerView recyclerView = new RecyclerView(getActivity());
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        TaskRecyclerAdapter mAdapter = new TaskRecyclerAdapter(mTitle) ;
        recyclerView.setAdapter(mAdapter);


//        TextView textView = new TextView(getActivity());
//        textView.setTextSize(24);
//        textView.setPadding(0,0,0,20);
//        textView.setBackgroundColor(Color.parseColor("#ffffffff"));
//        textView.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL);
//        textView.setText(mTitle);
        return recyclerView;
    }
}
