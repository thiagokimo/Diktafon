package io.kimo.diktafon.presentation.presenter;

import android.view.View;
import android.view.animation.LinearInterpolator;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.kimo.diktafon.presentation.view.MainView;
import io.kimo.diktafon.presentation.view.ui.fragment.VoiceRecorderFragment;

/**
 * Presenter of the main view
 */
public class MainPresenter {

    public static final int STATE_NOT_RECORDING = 0;
    public static final int STATE_RECORDING = 1;
    public static final int STATE_PAUSED = 2;

    private int currentState = STATE_NOT_RECORDING;

    private MainView mainView;
    private VoiceRecorderFragment voiceRecorderFragment;

    public MainPresenter(MainView mainView, VoiceRecorderFragment voiceRecorderFragment) {
        this.mainView = mainView;
        this.voiceRecorderFragment = voiceRecorderFragment;
    }

    public void performFABClick() {
        if(currentState == STATE_NOT_RECORDING) {
            displayVoiceRecorder();
            voiceRecorderFragment.initializeRecording();
            currentState = STATE_RECORDING;
        } else {

        }
    }

    private void displayVoiceRecorder() {
        mainView.showVoiceRecordView();
        mainView.showPauseButton();
        mainView.disableRecordList();
    }

    private void hideVoiceRecorderView() {
        mainView.hideVoiceRecordView();
        mainView.enableRecordList();
    }

    public void onDeleteRecord() {
        hideVoiceRecorderView();
        voiceRecorderFragment.finalizeRecording();
        currentState = STATE_NOT_RECORDING;
    }

    public SupportAnimator getAnimator(final View origin, final View container, final boolean show) {

        int cx = (origin.getLeft() + origin.getRight()) / 2;
        int cy = (origin.getBottom() + origin.getTop()) / 2;
        int radius = Math.max(container.getWidth(), container.getHeight());

        SupportAnimator animator;

        if(show) {
            animator = ViewAnimationUtils.createCircularReveal(container, cx, cy, 0, radius);
        } else {
            animator = ViewAnimationUtils.createCircularReveal(container, cx, cy, radius, 0);
        }
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(500);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {

                origin.setEnabled(false);

                if (show) {
                    container.setVisibility(View.VISIBLE);
                    mainView.showPauseButton();
                }
            }
            @Override
            public void onAnimationEnd() {

                origin.setEnabled(true);

                if(!show) {
                    container.setVisibility(View.INVISIBLE);
                    mainView.showRecordButton();
                }
            }
            @Override
            public void onAnimationCancel() {}
            @Override
            public void onAnimationRepeat() {}
        });

        return animator;
    }
}
