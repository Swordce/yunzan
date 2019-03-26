package com.liaoda.yunzan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoda.yunzan.R;
import com.liaoda.yunzan.yzFlowLayout.FlowTagLayout;

import java.util.ArrayList;
import java.util.List;

public class SponsorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<String> tags = new ArrayList<>();
    public SponsorAdapter(@Nullable List<String> data, List<String> tag) {
        super(R.layout.item_sponsor_brief_show, data);
        tags.addAll(tag);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if(helper.getAdapterPosition() == 1) {
            helper.setGone(R.id.ll_hint,true);
        }else {
            helper.setGone(R.id.ll_hint,false);
        }
        YzTagAdapter_PracticeSkill yzTagAdapter_practiceSkill = new YzTagAdapter_PracticeSkill(mContext);
        yzTagAdapter_practiceSkill.clearAndAddAll(tags);
        FlowTagLayout sponsor_item_tag_taglayout = (FlowTagLayout)helper.getView(R.id.sponsor_brief_show_tag_taglayout);
        sponsor_item_tag_taglayout.setTagCheckedMode(0);
        sponsor_item_tag_taglayout.setAdapter(yzTagAdapter_practiceSkill);
        yzTagAdapter_practiceSkill.notifyDataSetChanged();
    }
}
