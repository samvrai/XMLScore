package com.aeg.xmlscore;


import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * Created by will on 14/01/15.
 */
public class ClickListener implements View.OnClickListener {

    private Context ctx;
    private Note note;
    CheckBox cDotted, cNatural;
    RadioButton alterN, alterS, alterF;


    public ClickListener(Context ctx) {
        this.ctx = ctx;
        cDotted = (CheckBox)((FragmentActivity)ctx).findViewById(R.id.dotted);
        cNatural = (CheckBox)((FragmentActivity)ctx).findViewById(R.id.natural);
        alterN = (RadioButton)((FragmentActivity)ctx).findViewById(R.id.normal);
        alterF = (RadioButton)((FragmentActivity)ctx).findViewById(R.id.flat);
        alterS = (RadioButton)((FragmentActivity)ctx).findViewById(R.id.sharp);
    }

    @Override
    public void onClick(View v) {
        note = mNoteManager.getNoteManager().getNoteById(v.getId());
        mNoteManager.getNoteManager().setInTransaction(note);
        if(mNoteManager.getNoteManager().getInTransaction().isRest()) {
            alterN.setEnabled(false);
            alterF.setEnabled(false);
            alterS.setEnabled(false);
            cDotted.setEnabled(false);
            cNatural.setEnabled(false);
        } else {
            alterN.setEnabled(true);
            alterF.setEnabled(true);
            alterS.setEnabled(true);
            cDotted.setEnabled(true);
            cNatural.setEnabled(true);
        }

        ViewGroup vg = (ViewGroup)v.getParent();
        for(int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setBackground(null);
        }

        cDotted.setChecked(false);
        cNatural.setChecked(false);

        v.setBackground(ctx.getResources().getDrawable(R.drawable.shape));

        if(note.isFlagD()) {
            cDotted.setChecked(true);
        }
        if(note.isFlagN()) {
            cNatural.setChecked(true);
        }
        if(note.isFlagS()) {
            alterS.setChecked(true);
            alterF.setChecked(false);
            alterN.setChecked(false);
        }
        if(note.isFlagF()) {
            alterS.setChecked(false);
            alterF.setChecked(true);
            alterN.setChecked(false);
        }
        if(note.isFlagO()) {
            alterS.setChecked(false);
            alterF.setChecked(false);
            alterN.setChecked(true);
        }

        AdapterManager.getMaM().getNotesPager().setCurrentItem(2);
    }
}
