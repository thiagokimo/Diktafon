package io.kimo.diktafon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.kimo.diktafon.view.VoiceRecorderView;

/**
 * Fragment with the voice recorder timer
 */
public class VoiceRecorderFragment extends Fragment implements VoiceRecorderView {

    public static VoiceRecorderFragment newInstance() {
        return new VoiceRecorderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice_record, container, false);
        return view;
    }
}
