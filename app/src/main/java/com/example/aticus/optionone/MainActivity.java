package com.example.aticus.optionone;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    SlidingTabLayout mslidingTabLayout;
    ViewPager mviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true );
       /* NavigationDrawerFragment fragment= (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_drawer_navigation);
        fragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout),toolbar);*/
       //
      /*  contentFragment fragment=new contentFragment();
       // NavigationDrawerFragment fragment= new NavigationDrawerFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content,fragment,"frag");
        transaction.commit();


        mdrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle=new ActionBarDrawerToggle(this,mdrawerLayout,toolbar,R.string.Open_drawer,R.string.Open_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mdrawerLayout.setDrawerListener(mDrawerToggle);
        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });*/
        mslidingTabLayout= (SlidingTabLayout) findViewById(R.id.tabs);
        mviewPager= (ViewPager) findViewById(R.id.pager);
        mslidingTabLayout.setDistributeEvenly(true);
        mviewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mslidingTabLayout.setViewPager(mviewPager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    class MyPagerAdapter extends FragmentStatePagerAdapter
    {
        String[] tabs;
        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
            tabs=getResources().getStringArray(R.array.tabs);

        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment=null;
            if(position==0)
            {
                fragment= new TotalFragment();
            }
            if (position==2)
            {
                fragment= new DebitFragment();
            }
            if(position==1)
            {
                fragment=new CreditFragment();
            }

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount()
        {
            return 3;
        }
    }
}
