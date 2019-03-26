package com.liaoda.yunzan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liaoda.yunzan.adapter.YzHomeFragmentAdapter;
import com.liaoda.yunzan.fragment.DoingFragment;
import com.liaoda.yunzan.fragment.FinishFragment;
import com.liaoda.yunzan.fragment.HomeFragment;
import com.liaoda.yunzan.fragment.PriactiseFragment;
import com.liaoda.yunzan.fragment.SponsorFragment;

public class MyApplyActivity extends AppCompatActivity {
    private DoingFragment doingFragment;
    private FinishFragment finishFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("我申请的赞助");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initLocalData();
    }

    public void initLocalData() {
        doingFragment = new DoingFragment();
        finishFragment = new FinishFragment();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new YzHomeFragmentAdapter(getSupportFragmentManager(), this, new Fragment[]{this.doingFragment, this.finishFragment}, new String[]{"进行中的合作", "已完成的合作"}));
        viewPager.setOffscreenPageLimit(2);
        ((TabLayout) findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
