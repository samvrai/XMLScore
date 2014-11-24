package com.aeg.xmlscore;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by will on 20/11/14.
 */
public class DragListener implements View.OnDragListener {

    private Stage ctx;


    public DragListener(Stage ctx) {
        this.ctx = ctx;

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {


        switch (event.getAction()) {

            case DragEvent.ACTION_DRAG_ENTERED:
                if (v.getAlpha() == 1) {
                    v.setAlpha(0);
                }
                v.setAlpha(1);
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                if (v.getAlpha() == 0) {
                    v.setAlpha(1);
                }
                v.setAlpha(0);
                break;

            case DragEvent.ACTION_DROP:

                String name = this.droppedN(v);
                double weight = this.droppedW((ImageView) event.getLocalState());
                ((ImageView) event.getLocalState()).setDrawingCacheEnabled(true);



                ViewGroup newOwner = (ViewGroup)v.getParent();


                ImageView iv = new ImageView(ctx.getActivity());
                iv.setContentDescription(ctx.getString(R.string.added_notes));
                iv.setImageBitmap(((ImageView) event.getLocalState()).getDrawingCache());


                //Toast.makeText(ctx.getActivity(), iv.getHeight() + " " + iv.getWidth(), Toast.LENGTH_SHORT).show();

                if(v.getY() <= 400) {
                    iv.setRotationX(180);
                    iv.setRotationY(180);


                    iv.setY(v.getY() - 50);
                } else {

                    iv.setY(v.getY() - 230);

                }



                mNoteManager.getNoteManager().addNote(new Note(name, weight, ctx.getPosition(), ((ImageView) event.getLocalState()).getDrawingCache(), iv.getY()));
                mNoteManager.getNoteManager().reLocateStage(v.getWidth(), ctx.getPosition());
                newOwner.addView(iv);

                ctx.loadViews((ViewGroup)ctx.getView());

                if (v.getAlpha() == 0) {
                    v.setAlpha(1);
                }
                v.setAlpha(0);

                break;
        }
        return true;
    }

    private double droppedW(ImageView iv) {
        double weight = 0;

        switch (iv.getId()) {
            case R.id.whole:
                weight = 16;
                break;
            case R.id.half:
                weight = 8;
                break;
            case R.id.quarter:
                weight = 4;
                break;
            case R.id.eight:
                weight = 2;
                break;
            case R.id.sixteenth:
                weight = 1;
                break;
        }

        return weight;
    }

    private String droppedN(View v) {
        String name = null;
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

        return name;
    }


}



