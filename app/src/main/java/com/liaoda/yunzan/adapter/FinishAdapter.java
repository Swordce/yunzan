package com.liaoda.yunzan.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoda.yunzan.R;

import java.util.List;

public class FinishAdapter extends BaseQuickAdapter<Uri, BaseViewHolder> {

    public FinishAdapter(@Nullable List<Uri> data) {
        super(R.layout.uri_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Uri item) {

    }
}
