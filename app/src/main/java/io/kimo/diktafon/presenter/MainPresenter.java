package io.kimo.diktafon.presenter;

import android.view.View;
import android.view.animation.LinearInterpolator;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.kimo.diktafon.view.MainView;

/**
 * Presenter of the main view
 */
public class MainPresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void startRecording() {
        mainView.showVoiceRecordView();
        mainView.showPauseButton();
        mainView.disableRecordList();
    }

    private void stopRecording() {
        mainView.hideVoiceRecordView();
        mainView.enableRecordList();
    }

    public void onDeleteRecord() {
        stopRecording();
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
