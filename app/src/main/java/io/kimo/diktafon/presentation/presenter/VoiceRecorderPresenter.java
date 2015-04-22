package io.kimo.diktafon.presentation.presenter;

import io.kimo.diktafon.presentation.view.VoiceRecorderView;

/**
 * Responsible to control the main funcionalities of the voice recorder view
 */
public class VoiceRecorderPresenter {

    private VoiceRecorderView view;

    public VoiceRecorderPresenter(VoiceRecorderView view) {
        this.view = view;
    }

    public void assembleView() {
        view.startCounter();
    }

    public void cancelRecord() {
        view.pauseCounter();
        view.resetCounter();
    }
}
