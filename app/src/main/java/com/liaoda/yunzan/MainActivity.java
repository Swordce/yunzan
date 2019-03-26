package com.liaoda.yunzan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.liaoda.yunzan.adapter.YzHomeFragmentAdapter;
import com.liaoda.yunzan.fragment.HomeFragment;
import com.liaoda.yunzan.fragment.PriactiseFragment;
import com.liaoda.yunzan.fragment.SponsorFragment;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mDrawerToggle;
    private HomeFragment homeFragment;
    private PriactiseFragment priactiseFragment;
    private SponsorFragment sponsorFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView na = findViewById(R.id.nav_view);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerToggle.syncState();;///将ActionDrawerToggle与DrawerLayout的状态同步

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(na);
            }
        });

        na.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_sponsor:
                        startActivity(new Intent(MainActivity.this,MyApplyActivity.class));
                        mDrawerLayout.closeDrawer(na);
                        break;
                }
                return false;
            }
        });

        initLocalData();
    }

    public void initLocalData() {
        homeFragment = new HomeFragment();
        priactiseFragment = new PriactiseFragment();
        sponsorFragment = new SponsorFragment();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new YzHomeFragmentAdapter(getSupportFragmentManager(), this, new Fragment[]{this.homeFragment, this.priactiseFragment, this.sponsorFragment}, new String[]{"热点推荐", "外联招聘", "外联推广"}));
        viewPager.setOffscreenPageLimit(2);
        ((TabLayout) findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

}
