package io.kimo.diktafon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import com.melnykov.fab.FloatingActionButton;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private FrameLayout voiceRecordContainer;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapGUI();
        configureToolbar();
        configureFAB();
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

    private void configureToolbar() {


        setSupportActionBar(toolbar);
    }

    private void configureFAB() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(voiceRecordContainer.getVisibility() == View.VISIBLE) {
                    voiceRecordContainer.setVisibility(View.GONE);
                } else {
                    voiceRecordContainer.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
