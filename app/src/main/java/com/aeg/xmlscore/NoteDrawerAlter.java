package com.aeg.xmlscore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;


/**
 * Created by will on 19/11/14.
 */
public class NoteDrawerAlter extends Fragment {

    private CheckBox cDotted, cNatural;
    private RadioButton alterN, alterS, alterF;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alter, container, false);
        cDotted = (CheckBox)v.findViewById(R.id.dotted);
        cNatural = (CheckBox)v.findViewById(R.id.natural);
        alterN = (RadioButton)v.findViewById(R.id.normal);
        alterF = (RadioButton)v.findViewById(R.id.flat);
        alterS = (RadioButton)v.findViewById(R.id.sharp);
        return v;
    }

    public CheckBox getcNatural() {
        return cNatural;
    }

    public RadioButton getAlterN() {
        return alterN;
    }

    public RadioButton getAlterS() {
        return alterS;
    }

    public RadioButton getAlterF() {
        return alterF;
    }

    public CheckBox getcDotted() { return this.cDotted;}
}
