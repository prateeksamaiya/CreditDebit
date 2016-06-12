package com.example.aticus.optionone;


import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import DatabaseHandler.DatabaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditFragment extends Fragment implements DialogAlert.Communicator {


    private RecyclerView recyclerView;
    private static MyListCursorAdapter listCursorAdapteristAdapter;
    static Context context;
    private static final int VERTICAL_ITEM_SPACE = 24;
    private static DatabaseAdapter databaseAdapter;
    private DialogAlert dialogAlert=null;

    public CreditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        databaseAdapter = new DatabaseAdapter(getActivity());
        View view = inflater.inflate(R.layout.fragment_credit, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.credit_recyclerView);

      /*  recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getActivity(),position+" single",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View v, int position) {
                Toast.makeText(getActivity(),position+ " long",Toast.LENGTH_SHORT).show();

            }
        }));*/
        Cursor c = databaseAdapter.showAll();
        listCursorAdapteristAdapter = new MyListCursorAdapter(getActivity(), c);

        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE, getActivity()));
        recyclerView.setAdapter(listCursorAdapteristAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


               View child=recyclerView.getChildAt(viewHolder.getAdapterPosition());
               TextView textView= (TextView) child.findViewById(R.id.databaseId);
               long id=Long.parseLong(textView.getText().toString());
                deleteDialog(id);


            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if(viewHolder instanceof MyListCursorAdapter.ItemHeader)
                    return 0;
                return super.getSwipeDirs(recyclerView, viewHolder);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        return view;


    }

    @Override
    public void dialogSelection() {

        DatabaseAdapter.delete(dialogAlert.getColumnId());

    }

  /*  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener
    {
        GestureDetector gestureDetector;
        ClickListener clickListener;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener)
        {
            this.clickListener=clickListener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {

                    View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clickListener!=null)
                    {
                        int position=recyclerView.getChildAdapterPosition(child);
                        clickListener.onLongClick(child,position);
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e))
            {
                int position=rv.getChildAdapterPosition(child);
                clickListener.onClick(child, position);
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }*/

    public static void updateAdapter() {

        listCursorAdapteristAdapter.changeCursor(databaseAdapter.showAll());

    }


    public void deleteDialog(long columnToBeDeleted)
    {
        FragmentManager fm=getActivity().getFragmentManager();
        dialogAlert= new DialogAlert();
        dialogAlert.setColumnId(columnToBeDeleted,this);
        dialogAlert.show(fm, "Delete Alert");

    }
   /* public static interface ClickListener
    {
        public void onClick(View v,int position);
        public void onLongClick(View v,int position);
    }*/

}
