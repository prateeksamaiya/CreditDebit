package com.example.aticus.optionone;

/**
 * Created by aticus on 03-06-2016.
 */
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by skyfishjy on 10/31/14.
 */
public class MyListCursorAdapter extends CursorRecyclerViewAdapter<MyListCursorAdapter.ViewHolder>{

    private final String rupee;
    private final Resources res;

    public MyListCursorAdapter(Context context,Cursor cursor)
    {

        super(context,cursor);
        res=context.getResources();
        rupee=res.getString(R.string.Rs);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mAmount;
        public TextView mDateTime;
        public LinearLayout linearLayout;
        public ViewHolder(View view) {
            super(view);
            mAmount = (TextView) view.findViewById(R.id.amount);
            mDateTime = (TextView) view.findViewById(R.id.dateTime);
            linearLayout= (LinearLayout) view.findViewById(R.id.listLinearLayout);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class itemHeader extends RecyclerView.ViewHolder
    {

        public itemHeader(View itemView) {
            super(itemView);

        }
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor,int position) {
        MyListItem myListItem = MyListItem.fromCursor(cursor);
        if(position==0)
            viewHolder.linearLayout.setMinimumHeight(150);
        else
            viewHolder.linearLayout.setMinimumHeight(90);

        viewHolder.mAmount.setText(rupee+" "+myListItem.getAmount());
        viewHolder.mDateTime.setText(myListItem.getDateTime());
    }
}