package com.aeg.xmlscore;


import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;


/**
 * Created by will on 14/01/15.
 */
public class ClickListener implements View.OnClickListener {

    private Context ctx;
    private Note note;
    CheckBox cDotted;
    CheckBox cNatural;


    public ClickListener(Context ctx) {
        this.ctx = ctx;
        cDotted = (CheckBox)((FragmentActivity)ctx).findViewById(R.id.dotted);
        cNatural = (CheckBox)((FragmentActivity)ctx).findViewById(R.id.natural);
    }

    @Override
    public void onClick(View v) {

        ViewGroup vg = (ViewGroup)v.getParent();
        for(int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setBackground(null);
        }

        cDotted.setChecked(false);
        cNatural.setChecked(false);
        note = mNoteManager.getNoteManager().getNoteById(v.getId());
        v.setBackground(ctx.getResources().getDrawable(R.drawable.shape));
        AdapterManager.getMaM().getNotesPager().setCurrentItem(2);
        if(note.isFlagD()) {
            cDotted.setChecked(true);
        }
        if(note.isFlagN()) {
            cNatural.setChecked(true);
        }
    }
}
