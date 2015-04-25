package io.kimo.diktafon.presentation.presenter;

import android.media.MediaRecorder;

import io.kimo.diktafon.presentation.view.VoiceRecorderView;

/**
 * Responsible to control the main funcionalities of the voice recorder view
 */
public class VoiceRecorderPresenter {

    private VoiceRecorderView view;
    private MediaRecorder mediaRecorder;

    public VoiceRecorderPresenter(VoiceRecorderView view) {
        this.view = view;
        mediaRecorder = new MediaRecorder();
        configureMediaRecorder();
    }

    public void assembleView() {}

    public void cancelRecord() {
        view.pauseCounter();
        view.resetCounter();
    }

    public void startRecording() {
        view.resetCounter();
        view.startCounter();

//        mediaRecorder.start();
        
    }

    private void configureMediaRecorder() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

    }
}
