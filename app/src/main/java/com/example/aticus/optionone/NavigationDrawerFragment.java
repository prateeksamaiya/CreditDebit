package com.example.aticus.optionone;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.widget.Toast.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mdrawerLayout;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_drawer_navigation, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawer_list);
        adapter = new MyAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListner(getActivity(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View v, int position) {

                Toast.makeText(getActivity(),"single",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));

        return layout;
    }


    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int icons[] = {R.drawable.home, R.drawable.contact, R.drawable.map, R.drawable.calendar};
        String titles[] = {"HOME", "CALENDAR", "CONTACT", "MAP"};
        for (int i = 0; i < icons.length && i < titles.length; i++) {
            Information current = new Information();
            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            data.add(current);
        }
        return data;

    }

    void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
        mdrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.Open_drawer, R.string.Open_close) {
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
        });
    }


    class RecyclerTouchListner implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListner(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            Log.i("prateek", "constructer RecyclerTouchListener called");
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override

                public boolean onSingleTapUp(MotionEvent e) {

                    Log.i("prateek", "constructer OnSingleTapUp called");


                    return true;


                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                    Log.i("prateek", "constructer OnLongPress called");

                    super.onLongPress(e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
                Toast.makeText(getActivity(),"pratee",LENGTH_SHORT).show();
            }
            Log.i("prateek", "constructer OnInterceptTouchEvent called");

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.i("prateek", "constructer OnTouchEvent called");

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            Log.i("prateek", "constructer OnRequestDisallowIntercept called");

        }
    }

    public static interface ClickListener {
        public void onClick(View v, int position);

        public void onLongClick(View v, int position);
    }
}
