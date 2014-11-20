package com.aeg.xmlscore;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by will on 20/11/14.
 */
public class Hover implements View.OnHoverListener {

    private Context ctx;
    public Hover(Context pCtx) {
        ctx = pCtx;
    }
    @Override
    public boolean onHover(View v, MotionEvent event) {

        Toast.makeText(ctx, "I have received the event", Toast.LENGTH_SHORT).show();
        switch (event.getAction()) {
            case MotionEvent.ACTION_HOVER_ENTER:
                if(v.getId() == R.id.em || v.getId() == R.id.gm || v.getId() == R.id.bm || v.getId() == R.id.dM || v.getId() == R.id.fM || v.getId() == R.id.aM) {
                    v.setVisibility(View.INVISIBLE);
                }
                v.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                Toast.makeText(ctx, "Hover", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                if(v.getId() == R.id.em || v.getId() == R.id.gm || v.getId() == R.id.bm || v.getId() == R.id.dM || v.getId() == R.id.fM || v.getId() == R.id.aM) {
                    v.setVisibility(View.VISIBLE);
                }
                v.setVisibility(View.INVISIBLE);
                break;
        }
        return false;
    }
}
