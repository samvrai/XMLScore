package com.aeg.xmlscore;

import android.content.Context;
import android.support.v4.view.ViewPager;

/**
 * Created by will on 14/01/15.
 */
public class AdapterManager {

    private ViewPageAdapter pAdapter;
    private NotesAdapter nAdapter;
    private ViewPager vpager, notesPager;
    private static AdapterManager mAm;
    private Context ctx;


    private AdapterManager() {
    }

    public void loader(ViewPageAdapter vpa, NotesAdapter na, ViewPager vp, ViewPager np, Context ctxt) {
        this.pAdapter = vpa;
        this.nAdapter = na;
        this.vpager = vp;
        this.notesPager = np;
        this.ctx = ctxt;

    }

    public static AdapterManager getMaM() {
        if(mAm == null) {
            mAm = new AdapterManager();
        }
        return mAm;
    }

    public ViewPageAdapter getpAdapter() {
        return pAdapter;
    }

    public NotesAdapter getnAdapter() {
        return nAdapter;
    }

    public ViewPager getVpager() {
        return vpager;
    }

    public ViewPager getNotesPager() {
        return notesPager;
    }
}
