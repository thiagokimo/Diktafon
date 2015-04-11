package io.kimo.diktafon.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import io.kimo.diktafon.R;
import io.kimo.diktafon.presenter.VoiceRecorderPresenter;
import io.kimo.diktafon.view.VoiceRecorderView;

/**
 * Fragment with the voice recorder timer
 */
public class VoiceRecorderFragment extends Fragment implements VoiceRecorderView {

    private VoiceRecorderPresenter presenter;
    private Chronometer chronometer;

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
        presenter.assembleView();
    }

    private void mapGUI(View view) {
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
    }

    private void configureGUI() {
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    @Override
    public void startCounter() {
        chronometer.start();
    }

    @Override
    public void pauseCounter() {
        chronometer.stop();
    }
}
