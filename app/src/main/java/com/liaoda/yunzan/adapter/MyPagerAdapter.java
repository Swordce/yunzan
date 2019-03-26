package com.liaoda.yunzan.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.liaoda.yunzan.R;

import java.util.List;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time:2017/12/6 10:47.
 *    *     *   *         *   * *       Email address:ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site:www.ocnyang.com
 *******************************************************************/


public class MyPagerAdapter extends PagerAdapter {

    private List<Integer> mData;
    private Context mContext;

    public MyPagerAdapter(List<Integer> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_banner_item, container, false);
        ImageView imageView1 = (ImageView) inflate.findViewById(R.id.img_card_item);
        imageView1.setImageResource(mData.get(position));
        container.addView(inflate);
        return inflate;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }
}