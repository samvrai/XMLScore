package com.aeg.xmlscore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by will on 19/11/14.
 *
 */
public class NoteDrawerNotes extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notes, container, false);

        ImageView iv = (ImageView)v.findViewById(R.id.whole);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.half);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.quarter);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.eighth);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.sixteenth);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.thirtytwoth);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.sixtyfourth);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));


        return v;
    }

}
