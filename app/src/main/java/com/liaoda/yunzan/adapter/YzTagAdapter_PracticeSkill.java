package com.liaoda.yunzan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liaoda.yunzan.R;
import com.liaoda.yunzan.yzFlowLayout.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;

public class YzTagAdapter_PracticeSkill extends BaseAdapter implements OnInitSelectedPosition {
    private final Context mContext;
    private final List<String> mDataList = new ArrayList();

    public YzTagAdapter_PracticeSkill(Context context) {
        this.mContext = context;
    }

    public int getCount() {
        return this.mDataList.size();
    }

    public Object getItem(int position) {
        return this.mDataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.tag_item_practice_item_skill, null);
        ((TextView) view.findViewById(R.id.tv_tag)).setText((String) this.mDataList.get(position));
        return view;
    }

    public void onlyAddAll(List<String> datas) {
        this.mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<String> datas) {
        this.mDataList.clear();
        onlyAddAll(datas);
    }

    public boolean isSelectedPosition(int position) {
        return false;
    }
}
