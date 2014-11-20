package com.aeg.xmlscore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by will on 20/11/14.
 */
public class DragListener implements View.OnDragListener {

    private Stage ctx;

    private int pos = 50;

    public DragListener(Stage ctx) {
        this.ctx = ctx;

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        String name = null;
        double weight = 0;

        switch (event.getAction()) {

            case DragEvent.ACTION_DRAG_ENTERED:
                if(v.getAlpha() == 1) {
                    v.setAlpha(0);
                }
                v.setAlpha(1);
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                if(v.getAlpha() == 0) {
                    v.setAlpha(1);
                }
                v.setAlpha(0);
                break;

            case DragEvent.ACTION_DROP:

                switch (v.getId()) {
                 case R.id.am:
                        name = "am";
                     break;
                    case R.id.aMb:
                     name = "aM";
                     break;
                 case R.id.bM:
                     name = "bM";
                     break;
                 case R.id.bmb:
                     name = "bm";
                     break;
                 case R.id.cm:
                     name = "cm";
                     break;
                 case R.id.cM:
                     name = "cM";
                     break;
                 case R.id.cMM:
                     name = "cMM";
                     break;
                 case R.id.dm:
                     name = "dm";
                     break;
                 case R.id.dMb:
                     name = "dM";
                     break;
                 case R.id.eM:
                     name = "eM";
                     break;
                 case R.id.emb:
                     name = "em";
                     break;
                 case R.id.fm:
                     name = "fm";
                     break;
                 case R.id.fMb:
                     name = "fM";
                     break;
                 case R.id.gM:
                     name = "gM";
                     break;
                 case R.id.gmb:
                     name = "gm";
                     break;
             }

            ImageView iv = (ImageView)event.getLocalState();
            switch (iv.getId()) {
                case R.id.whole:
                    weight = 1;
                    break;
                case R.id.half:
                    weight = 0.5;
                    break;
                case R.id.quarter:
                    weight = 0.25;
                    break;
                case R.id.eight:
                    weight = 0.125;
                    break;
                case R.id.sixteenth:
                    weight = 0.0625;
                    break;
            }
            ctx.addNote(new Note(name, weight));
                if(v.getAlpha() == 0) {
                    v.setAlpha(1);
                }
                v.setAlpha(0);

            break;
        }

        return true;
    }
}
