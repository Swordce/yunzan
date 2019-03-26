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
import com.liaoda.yunzan.adapter.PractiseAdapter;
import com.liaoda.yunzan.adapter.SponsorAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

public class PriactiseFragment extends Fragment {
    private View cv;
    private RecyclerView rvSubject;
    private PractiseAdapter homeAdapter;
    private View headerView;
    private View headerView2;
    private View headerView3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cv = inflater.inflate(R.layout.layout_sponsosr,container,false);
        return cv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvSubject = cv.findViewById(R.id.rv_subject);
        headerView3 = getLayoutInflater().inflate(R.layout.practise_header,(ViewGroup)rvSubject.getParent(),false);
        rvSubject.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testList.add(i+"");
        }
        List<String> tags = new ArrayList<>();
        tags.add("校园摆点");
        tags.add("传单派发");
        tags.add("产品促销");
        homeAdapter = new PractiseAdapter(testList,tags);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.divier));
        rvSubject.addItemDecoration(divider);
        homeAdapter.addHeaderView(headerView3);
        rvSubject.setAdapter(homeAdapter);
    }

}
