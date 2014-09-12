package com.aeg.xmlscore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by nemo on 9/12/14.
 */
public class StaveDrawer extends View {

    Paint black, blue = new Paint();

    public StaveDrawer(Context pCont) {
        super(pCont);
        black.setColor(Color.BLACK);
        blue.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(Canvas pCanv) {
        pCanv.drawLine(100,100, 300, 20, this.black);
    }
}
