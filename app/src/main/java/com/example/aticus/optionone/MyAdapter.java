package com.example.aticus.optionone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by aticus on 19-05-2016.
 */
public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>
{
    List<Information> data= Collections.emptyList();
    private final LayoutInflater inflator;
    private Context context;

    MyAdapter(Context context,List<Information> data)
    {

        inflator =LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflator.inflate(R.layout.custom_layout, parent, false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.MyViewHolder holder, int position)
    {
        Information current =data.get(position);
        holder.listText.setText(current.getTitle());
        holder.listIcon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView listText;
        ImageView listIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            listIcon= (ImageView) itemView.findViewById(R.id.listIcon);
            listText= (TextView) itemView.findViewById(R.id.listText);
        }


    }



}
