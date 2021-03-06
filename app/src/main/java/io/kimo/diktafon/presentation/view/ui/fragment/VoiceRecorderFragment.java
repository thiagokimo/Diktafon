package io.kimo.diktafon.presentation.view.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import io.kimo.diktafon.R;
import io.kimo.diktafon.presentation.presenter.VoiceRecorderPresenter;
import io.kimo.diktafon.presentation.view.VoiceRecorderView;

/**
 * Fragment with the voice recorder timer
 */
public class VoiceRecorderFragment extends Fragment implements VoiceRecorderView {

    public interface VoiceRecorderButtonListener {
        void onTrashButtonClicked();
        void onCheckButtonClicked();
    }

    private VoiceRecorderButtonListener voiceRecorderButtonListener;

    private VoiceRecorderPresenter presenter;
    private Chronometer chronometer;
    private View deleteButton;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof VoiceRecorderButtonListener) {
            voiceRecorderButtonListener = (VoiceRecorderButtonListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice_record, container, false);
        mapGUI(view);
        configureGUI();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new VoiceRecorderPresenter(this);
    }

    private void mapGUI(View view) {
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        deleteButton = view.findViewById(R.id.delete);
    }

    private void configureGUI() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceRecorderButtonListener.onTrashButtonClicked();
                presenter.cancelRecord();
            }
        });
    }

    public void initializeRecording() {
        presenter.startRecording();
    }

    public void finalizeRecording() {
        presenter.cancelRecord();
    }

    @Override
    public void startCounter() {
        chronometer.start();
    }

    @Override
    public void pauseCounter() {
        chronometer.stop();
    }

    @Override
    public void resetCounter() {
        chronometer.setBase(SystemClock.elapsedRealtime());
    }
}
