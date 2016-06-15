package com.example.aticus.optionone;

/**
 * Created by aticus on 03-06-2016.
 */

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import DatabaseHandler.DatabaseAdapter;
import Utility.DateFormat;

/**
 * Created by skyfishjy on 10/31/14.
 */
public class MyListCursorAdapter extends CursorRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private final String rupee;
    private final Resources res;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    static Context context;


    public MyListCursorAdapter(Context context, Cursor cursor) {

        super(context, cursor);
        this.context=context;
        res = context.getResources();
        rupee = res.getString(R.string.Rs);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mAmount;
        public TextView mDateTime;
        private TextView mDataBaseId;

        public ViewHolder(View view) {
            super(view);
            mAmount = (TextView) view.findViewById(R.id.amount);
            mDateTime = (TextView) view.findViewById(R.id.dateTime);
            mDataBaseId= (TextView) view.findViewById(R.id.databaseId);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        RecyclerView.ViewHolder vh = null;
        if (viewType == TYPE_ITEM) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
            vh = new ViewHolder(itemView);

        } else if (viewType == TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header_layout, parent, false);
            vh = new ItemHeader(itemView);
        }

        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_ITEM;

    }


    public static class ItemHeader extends RecyclerView.ViewHolder {
        TextView Debittotal;
        TextView Credittotal;
        TextView totalFigure;


        public ItemHeader(View itemView) {
            super(itemView);

            totalFigure = (TextView) itemView.findViewById(R.id.totalfigure);
            Credittotal = (TextView) itemView.findViewById(R.id.credittotalfigure);
            Debittotal = (TextView) itemView.findViewById(R.id.debittotalfigure);


        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, Cursor cursor, int position) {
        MyListItem myListItem;

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            myListItem = MyListItem.fromCursor(cursor);
            if (cursor.getInt(3) == 0)
                viewHolder.mAmount.setTextColor(Color.parseColor("#006600"));
            else
                viewHolder.mAmount.setTextColor(Color.parseColor("#ED1C24"));

            viewHolder.mAmount.setText(rupee + " " + myListItem.getAmount());
            viewHolder.mDateTime.setText(DateFormat.ConvertDate(myListItem.getDateTime()));
            viewHolder.mDataBaseId.setText(myListItem.get_id()+"");
        } else {


            long creditTotal=DatabaseAdapter.CreditSum();
            long debitTotal=DatabaseAdapter.DebitSum();
            long total=creditTotal-debitTotal;


            ItemHeader itemHeader = (MyListCursorAdapter.ItemHeader) holder;


            if(total>0)
                itemHeader.totalFigure.setTextColor(Color.parseColor("#006600"));
            else
                itemHeader.totalFigure.setTextColor(Color.parseColor("#ED1C24"));
            itemHeader.Credittotal.setTextColor(Color.parseColor("#006600"));
            itemHeader.Debittotal.setTextColor(Color.parseColor("#ED1C24"));



            itemHeader.totalFigure.setText(rupee + " " + Math.abs(total) + "");
            itemHeader.Credittotal.setText(rupee + " " + creditTotal + "");
            itemHeader.Debittotal.setText(rupee + " " + debitTotal + "");



        }


    }


}