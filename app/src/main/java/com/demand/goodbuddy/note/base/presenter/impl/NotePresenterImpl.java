package com.demand.goodbuddy.note.base.presenter.impl;

import android.view.View;

import com.demand.goodbuddy.note.base.interactor.NoteInteractor;
import com.demand.goodbuddy.note.base.interactor.impl.NoteInteractorImpl;
import com.demand.goodbuddy.note.base.presenter.NotePresenter;
import com.demand.goodbuddy.note.base.view.NoteView;

/**
 * Created by ㅇㅇ on 2017-08-03.
 */

public class NotePresenterImpl implements NotePresenter {
    private NoteInteractor noteInteractor;
    private NoteView noteView;

    public NotePresenterImpl(NoteView noteView) {
        this.noteInteractor = new NoteInteractorImpl(this);
        this.noteView = noteView;
    }

    @Override
    public void init(){
        noteView.init();

        View decorView = noteView.getDecorView();
        noteView.setToolbar(decorView);
        noteView.showToolbarTitle("건강 관리 수첩");
    }
}
