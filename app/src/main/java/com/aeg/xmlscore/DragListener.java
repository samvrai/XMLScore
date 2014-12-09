package com.aeg.xmlscore;

import android.util.Log;
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


                /**
                 * Collect the note variables:
                 *      Name of the note.
                 *      Weight of the note.
                 *
                 * Enable Drawing Cache to clone the bitmap.
                 */

                String name = mTools.getTools().droppedN(v.getId());
                ImageView target = (ImageView) event.getLocalState();
                float weight = mTools.getTools().droppedW(target.getId());
                ((ImageView) event.getLocalState()).setDrawingCacheEnabled(true);


                /**
                 * Get the View instance that will contain the note.
                 *
                 * Create a new image and set variables, including bitmap.
                 */
                ViewGroup newOwner = (ViewGroup) ctx.getActivity().findViewById(R.id.notePlace);


                ImageView iv = new ImageView(ctx.getActivity());
                iv.setContentDescription(ctx.getString(R.string.added_notes));
                iv.setImageBitmap(((ImageView) event.getLocalState()).getDrawingCache());

                /**
                 * If the note position is lower or equal to 400 (Note A or higher. Screen measuring
                 * starts from (Upper, Left) corner) rotate image to set stem downwards.
                 *
                 * Apply correction to image situation. Image positioning is made by (Upper, Left) corner.
                 * To center it, correction is needed.
                 */
                //Toast.makeText(ctx.getActivity(), iv.getHeight() + " " + iv.getWidth(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(ctx.getActivity(), name, Toast.LENGTH_SHORT).show();

                if(mMeasureCounter.getmMC().check(weight, mNoteManager.getNoteManager().stageWeight(ctx.getPosition()))) {
                    this.doAdding(name, weight, v, iv, event, newOwner);
                } else {
                    Toast.makeText(ctx.getActivity(), R.string.error_add, Toast.LENGTH_SHORT).show();
                }





                if (v.getAlpha() == 0) {
                    v.setAlpha(1);
                }
                v.setAlpha(0);

                break;
        }
        return true;
    }

    private void doAdding(String name, float weight, View v, ImageView iv, DragEvent event, ViewGroup newOwner) {
        mNoteManager.getNoteManager().addNote(new Note(name, weight, ctx.getPosition(), ((ImageView) event.getLocalState()).getDrawingCache(), v.getY()));

        if(v.getY() <= 400) {
            iv.setRotationX(180);
            iv.setRotationY(180);


            iv.setY(v.getY() - 50);
        } else {

            iv.setY(v.getY() - 230);

        }


        /**
         * Store note and relocate all the notes in the current stave to fit them.
         * Reload the views.
         *
         * Ends returning lines to original state.
         */


        newOwner.addView(iv);
        mTools.getTools().relocate(this.ctx.getPosition(), newOwner);
        //Toast.makeText(ctx.getActivity(), String.valueOf(iv.getY()), Toast.LENGTH_SHORT).show();

        if(mMeasureCounter.getmMC().getMax() == mNoteManager.getNoteManager().stageWeight(ctx.getPosition())) {
            Stave ma = (Stave)ctx.getActivity();
            ma.addStage();
        }
    }

}



