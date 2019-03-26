package com.liaoda.yunzan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoda.yunzan.R;

public class DoingFragment extends Fragment {

    private View cv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cv = inflater.inflate(R.layout.doing_fragment,container,false);
        return cv;
    }
}
