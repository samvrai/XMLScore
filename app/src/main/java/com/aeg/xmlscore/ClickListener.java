package com.aeg.xmlscore;


import android.view.View;
import android.view.ViewGroup;


/**
 * Created by will on 14/01/15.
 */
public class ClickListener implements View.OnClickListener {


    public ClickListener() {
    }

    @Override
    public void onClick(View v) {
        NotesAdapter na =AdapterManager.getMaM().getnAdapter();
        Note note = mNoteManager.getNoteManager().getNoteById(v.getId());
        if(mNoteManager.getNoteManager().getInTransaction().isRest()) {

            na.getAlterN().setEnabled(false);
            na.getAlterF().setEnabled(false);
            na.getcDotted().setEnabled(false);
            na.getAlterS().setEnabled(false);
            na.getcNatural().setEnabled(false);
        } else {
            na.getAlterN().setEnabled(true);
            na.getAlterF().setEnabled(true);
            na.getAlterS().setEnabled(true);
            na.getcDotted().setEnabled(true);
            na.getcNatural().setEnabled(true);
        }

        ViewGroup vg = (ViewGroup)v.getParent();
        for(int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setBackground(null);
        }

        na.getcDotted().setChecked(false);
        na.getcNatural().setChecked(false);

        v.setBackground(vg.getResources().getDrawable(R.drawable.shape));

        if(note.isFlagD()) {
            na.getcDotted().setChecked(true);
        }
        if(note.isFlagN()) {
            na.getcNatural().setChecked(true);
        }
        if(note.isFlagS()) {
            na.getAlterS().setChecked(true);
            na.getAlterF().setChecked(false);
            na.getAlterN().setChecked(false);
        }
        if(note.isFlagF()) {
            na.getAlterS().setChecked(false);
            na.getAlterF().setChecked(true);
            na.getAlterN().setChecked(false);
        }
        if(note.isFlagO()) {
            na.getAlterS().setChecked(false);
            na.getAlterF().setChecked(false);
            na.getAlterN().setChecked(true);
        }

        AdapterManager.getMaM().getNotesPager().setCurrentItem(2);
    }
}
