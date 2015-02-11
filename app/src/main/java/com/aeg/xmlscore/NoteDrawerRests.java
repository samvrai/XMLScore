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
public class NoteDrawerRests extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rests, container, false);

        ImageView iv = (ImageView)v.findViewById(R.id.wholerest);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.halfrest);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.quarterrest);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.eighthrest);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.sixteenthrest);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.thirtytwothrest);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        iv = (ImageView)v.findViewById(R.id.sixtyfourthrest);
        iv.setOnLongClickListener(new LCListener(this.getActivity()));

        return v;
    }
}