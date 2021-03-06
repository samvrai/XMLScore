package com.aeg.xmlscore;

import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by will on 20/11/14.
 *
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
                if (v.getAlpha() == 0.3f) {
                    v.setAlpha(0);
                }
                v.setAlpha(1);
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                if (v.getAlpha() == 0) {
                    v.setAlpha(0.3f);
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

                String name;
                int octave;
                ImageView target = (ImageView) event.getLocalState();
                boolean rest = mTools.getTools().isRest(target.getId());
                if(!rest) {
                    name = mTools.getTools().droppedN(v.getId());
                    octave = mTools.getTools().droppedOctave(v.getId());
                } else {
                    name = "R";
                    octave = 0;
                }
                float weight = mTools.getTools().droppedW(target.getId());
                String type = mTools.getTools().droppedT(target.getId());
                /**
                 * Get the View instance that will contain the note.
                 *
                 * Create a new image and set variables, including bitmap.
                 */
                ViewGroup newOwner = ctx.getNotePlace();


                /**
                 * If the note position is lower or equal to 400 (Note A or higher. Screen measuring
                 * starts from (Upper, Left) corner) rotate image to set stem downwards.
                 *
                 * Apply correction to image situation. Image positioning is made by (Upper, Left) corner.
                 * To center it, correction is needed.
                 */

                if(mMeasureCounter.getmMC().check(weight, mNoteManager.getNoteManager().stageWeight(ctx.getPosition()))) {
                    this.doAdding(name, weight, event, newOwner, octave, type, rest);
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

    private void doAdding(String name, float weight, DragEvent event, ViewGroup newOwner, int octave, String type, boolean rest) {
        Note note = new Note(name, weight, ctx.getPosition(), octave, type, rest);

        boolean added = false;
        for(int i = 0; i < newOwner.getChildCount() && !added; i++) {
            if(event.getX() < newOwner.getChildAt(i).getX()) {
                mNoteManager.getNoteManager().addNote(note, i);
                added = true;
            }
        }
        if(!added) {
            mNoteManager.getNoteManager().addNote(note);
        }
        AdapterManager.getMaM().updateTexts();

        mTools.getTools().relocate(this.ctx.getPosition(), newOwner);

        if(mMeasureCounter.getmMC().getMax() == mNoteManager.getNoteManager().stageWeight(ctx.getPosition())) {
            Stave ma = (Stave)ctx.getActivity();
            ma.addStage(null);
        }
    }

}



