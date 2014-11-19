package com.aeg.xmlscore;

/**
 * Created by will on 19/11/14.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Liner extends View {
    Paint paint = new Paint();

    public Liner(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(20, 5, 200, 5, paint);
        
    }

}