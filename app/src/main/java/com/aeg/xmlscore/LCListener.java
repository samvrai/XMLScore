package com.aeg.xmlscore;

import android.content.ClipData;
import android.content.Context;
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

        ClipData data = ClipData.newPlainText("", "");

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(50);
        v.startDrag(data, new View.DragShadowBuilder(v), v, 0);
        return false;
    }

}


