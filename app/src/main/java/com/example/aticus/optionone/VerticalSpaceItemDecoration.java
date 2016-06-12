package com.example.aticus.optionone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by aticus on 05-06-2016.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight;
    private Context context;

    public VerticalSpaceItemDecoration(int mVerticalSpaceHeight,Context context) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        this.context=context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (position == 0) {
            ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
            int layout=layoutparams.width;
            outRect.top=40;
            outRect.bottom = 50;
            outRect.left=(width-layout)/2;
            outRect.right=20;
        } else {
            outRect.bottom = mVerticalSpaceHeight;
            outRect.left = mVerticalSpaceHeight;
            outRect.right = mVerticalSpaceHeight;
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

    }
}