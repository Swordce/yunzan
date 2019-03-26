package com.liaoda.yunzan.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoda.yunzan.R;

import java.util.List;

public class HomeHeaderAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public HomeHeaderAdapter(@Nullable List<Integer> data) {
        super(R.layout.lalyout_home_header_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.setImageResource(R.id.iv,item);
    }
}
