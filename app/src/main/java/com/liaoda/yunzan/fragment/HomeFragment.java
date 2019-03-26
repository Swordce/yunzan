package com.liaoda.yunzan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liaoda.yunzan.R;
import com.liaoda.yunzan.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

public class HomeFragment extends Fragment {
    private View cv;
    private RecyclerView rvSubject;
    private HomeAdapter homeAdapter;
    private View headerView;
    private View headerView2;
    private View headerView3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cv = inflater.inflate(R.layout.layout_fragment,container,false);
        return cv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvSubject = cv.findViewById(R.id.rv_subject);
        headerView = getLayoutInflater().inflate(R.layout.item_practice_brief_show,(ViewGroup)rvSubject.getParent(),false);
        headerView2 = getLayoutInflater().inflate(R.layout.item_practice_brief_show,(ViewGroup)rvSubject.getParent(),false);
        headerView3 = getLayoutInflater().inflate(R.layout.header_line,(ViewGroup)rvSubject.getParent(),false);
        LinearLayout llHint = headerView2.findViewById(R.id.ll_hint);
        llHint.setVisibility(View.GONE);
        rvSubject.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testList.add(i+"");
        }
        List<String> tags = new ArrayList<>();
        tags.add("校园摆点");
        tags.add("传单派发");
        tags.add("产品促销");
        homeAdapter = new HomeAdapter(testList,tags);
        homeAdapter.setHeaderView(headerView,0);
        homeAdapter.setHeaderView(headerView2,1);
        homeAdapter.setHeaderView(headerView3,2);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.divier));
        rvSubject.addItemDecoration(divider);
        rvSubject.setAdapter(homeAdapter);

    }

}
