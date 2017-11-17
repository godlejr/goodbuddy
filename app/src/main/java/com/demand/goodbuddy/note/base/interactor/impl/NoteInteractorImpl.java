package com.demand.goodbuddy.note.base.interactor.impl;

import com.demand.goodbuddy.note.base.interactor.NoteInteractor;
import com.demand.goodbuddy.note.base.presenter.NotePresenter;

/**
 * Created by ㅇㅇ on 2017-08-03.
 */

public class NoteInteractorImpl implements NoteInteractor {
    private NotePresenter notePresenter;

    public NoteInteractorImpl(NotePresenter notePresenter) {
        this.notePresenter = notePresenter;
    }
}
