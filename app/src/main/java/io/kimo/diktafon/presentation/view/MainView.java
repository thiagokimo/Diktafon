package io.kimo.diktafon.presentation.view;

/**
 * View with the FAB
 */
public interface MainView {

    void showVoiceRecordView();
    void hideVoiceRecordView();

    void showRecordButton();
    void showPauseButton();

    void enableRecordList();
    void disableRecordList();

}
