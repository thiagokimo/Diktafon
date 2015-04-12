package io.kimo.diktafon.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import com.melnykov.fab.FloatingActionButton;

import io.kimo.diktafon.R;
import io.kimo.diktafon.presenter.MainPresenter;
import io.kimo.diktafon.ui.fragment.VoiceRecorderFragment;
import io.kimo.diktafon.view.MainView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends ActionBarActivity implements MainView, VoiceRecorderFragment.VoiceRecorderButtonListener {

    private Toolbar toolbar;
    private FrameLayout voiceRecordContainer;
    private FloatingActionButton floatingActionButton;

    private MainPresenter mainPresenter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapGUI();
        configureGUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void mapGUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        voiceRecordContainer = (FrameLayout) findViewById(R.id.voice_record_container);
    }

    private void configureGUI() {
        mainPresenter = new MainPresenter(this);

        setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.startRecording();
            }
        });
    }

    @Override
    public void showVoiceRecordView() {
        voiceRecordContainer.post(new Runnable() {
            @Override
            public void run() {
                mainPresenter.getAnimator(floatingActionButton, voiceRecordContainer, true).start();
            }
        });
    }

    @Override
    public void hideVoiceRecordView() {
        voiceRecordContainer.post(new Runnable() {
            @Override
            public void run() {
                mainPresenter.getAnimator(floatingActionButton, voiceRecordContainer, false).start();
            }
        });
    }

    @Override
    public void showRecordButton() {
        floatingActionButton.setColorNormal(getResources().getColor(R.color.primary));
        floatingActionButton.setImageResource(R.drawable.abc_ic_voice_search_api_mtrl_alpha);
    }

    @Override
    public void showPauseButton() {
        floatingActionButton.setColorNormal(Color.WHITE);
        floatingActionButton.setImageResource(R.drawable.fab_pause);
    }

    @Override
    public void onDeleteButtonClicked() {
        mainPresenter.onDeleteRecord();
    }

    @Override
    public void checkButtonClicked() {

    }
}
