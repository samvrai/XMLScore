package com.aeg.xmlscore;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.View;

/**
 * Created by will on 19/11/14.
 */
public class LCListener implements View.OnLongClickListener {

    private Context context = null;

    public LCListener(Context ctx) {
        context = ctx;
    }



    @Override
    public boolean onLongClick(View v) {

        DragShadower ds = new DragShadower(v);
        ClipData data = ClipData.newPlainText("", "");

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(50);
        v.startDrag(data, ds, v, 0);
        return false;
    }


    private class DragShadower extends View.DragShadowBuilder {

        ColorDrawable colorDrawable;


        private DragShadower(View view) {
            super(view);
            colorDrawable = new ColorDrawable(Color.LTGRAY);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            colorDrawable.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {

            View view = getView();

            int h = view.getHeight();
            int w = view.getWidth();

            colorDrawable.setBounds(0, 0, w, h);
            shadowSize.set(w, h);
            shadowTouchPoint.set((int)w/2, (int)h/2);

        }
    }

}


