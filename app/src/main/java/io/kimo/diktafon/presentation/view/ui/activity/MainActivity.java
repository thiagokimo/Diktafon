package io.kimo.diktafon.presentation.view.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import com.melnykov.fab.FloatingActionButton;

import io.kimo.diktafon.R;
import io.kimo.diktafon.presentation.presenter.MainPresenter;
import io.kimo.diktafon.presentation.view.MainView;
import io.kimo.diktafon.presentation.view.ui.fragment.VoiceRecorderFragment;


public class MainActivity extends AppCompatActivity implements MainView, VoiceRecorderFragment.VoiceRecorderButtonListener {

    private Toolbar toolbar;
    private FrameLayout voiceRecordContainer, recordListContainer;
    private FloatingActionButton floatingActionButton;

    private VoiceRecorderFragment voiceRecorderFragment;

    private MainPresenter mainPresenter;

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

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
        recordListContainer = (FrameLayout) findViewById(R.id.records_list_container);

        voiceRecorderFragment = (VoiceRecorderFragment) getSupportFragmentManager().findFragmentById(R.id.voice_recorder);
    }

    private void configureGUI() {
        mainPresenter = new MainPresenter(this, voiceRecorderFragment);

        setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.performFABClick();
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
    public void enableRecordList() {
        voiceRecordContainer.setClickable(false);
    }

    @Override
    public void disableRecordList() {
        voiceRecordContainer.setClickable(true);
    }

    @Override
    public void onTrashButtonClicked() {
        mainPresenter.onDeleteRecord();
    }

    @Override
    public void onCheckButtonClicked() {

    }
}
