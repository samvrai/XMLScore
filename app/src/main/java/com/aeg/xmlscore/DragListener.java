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
                iv.setOnClickListener(new ClickListener(ctx.getActivity()));
                iv.setContentDescription(ctx.getString(R.string.added_notes));
                iv.setImageBitmap(((ImageView) event.getLocalState()).getDrawingCache());

                float y = mNoteManager.getNoteManager().getNoteY(name);

                //Toast.makeText(ctx.getActivity(), String.valueOf(y), Toast.LENGTH_SHORT).show();

                if(y < 450) {
                    iv.setRotationX(180);
                    iv.setRotationY(180);
                    y -= 50;
                } else {
                    y -= 230;
                }
                iv.setY(y);

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
                    this.doAdding(name, weight, iv, event, newOwner, v.getX());
                } else {
                    Toast.makeText(ctx.getActivity(), R.string.error_add, Toast.LENGTH_SHORT).show();
                }

                if (v.getAlpha() == 0) {
                    v.setAlpha(1);
                }
                v.setAlpha(0);
        }
        return true;
    }

    private void doAdding(String name, float weight, ImageView iv, DragEvent event, ViewGroup newOwner, float pos) {
        Note note = new Note(name, weight, ctx.getPosition(), ((ImageView) event.getLocalState()).getDrawingCache());
        iv.setId(note.getId());

        boolean added = false;
        for(int i = 0; i < newOwner.getChildCount(); i++) {
            if(pos < newOwner.getChildAt(i).getX()) {
                mNoteManager.getNoteManager().addNote(note, i);
                added = true;
                ((ViewGroup)iv.getParent()).removeView(iv);
                newOwner.addView(iv, i);
            }
        }
        if(!added) {
            mNoteManager.getNoteManager().addNote(note);
            newOwner.addView(iv);
        }
        /**
         * Store note and relocate all the notes in the current stave to fit them.
         * Reload the views.
         *
         * Ends returning lines to original state.
         */

        mTools.getTools().relocate(this.ctx.getPosition(), newOwner);
        //Toast.makeText(ctx.getActivity(), String.valueOf(iv.getY()), Toast.LENGTH_SHORT).show();

        if(mMeasureCounter.getmMC().getMax() == mNoteManager.getNoteManager().stageWeight(ctx.getPosition())) {
            Stave ma = (Stave)ctx.getActivity();
            ma.addStage(null);
        }
    }

}



