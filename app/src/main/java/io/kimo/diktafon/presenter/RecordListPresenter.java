package io.kimo.diktafon.presenter;

import android.content.Context;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;

import java.util.ArrayList;
import java.util.List;

import io.kimo.diktafon.model.RecordModel;
import io.kimo.diktafon.view.RecordListView;

/**
 * Handles the display logic of the RecordListView
 */
public class RecordListPresenter {

    private RecordListView view;
    private Context context;

    public RecordListPresenter(Context context, RecordListView view) {
        this.view = view;
        this.context = context;
    }

    public void assembleView() {
        hideAllViews();
        view.showLoading();

        Tasks.executeInBackground(context, new BackgroundWork<List<RecordModel>>() {
            @Override
            public List<RecordModel> doInBackground() throws Exception {
                return generateRandomRecords(100);
            }
        }, new Completion<List<RecordModel>>() {
            @Override
            public void onSuccess(Context context, List<RecordModel> recordModels) {
                view.hideLoading();

                if (recordModels.isEmpty()) {
                    view.showEmpty("There are no local records.");
                } else {
                    view.renderRecordList(recordModels);
                    view.showList();
                }

            }

            @Override
            public void onError(Context context, Exception e) {
                view.hideLoading();
                view.showEmpty("Error on reading local records.");
            }
        });
    }

    private void hideAllViews() {
        view.hideList();
        view.hideLoading();
        view.hideEmpty();
    }

    private List<RecordModel> generateRandomRecords(int numberOfRecords) {
        List<RecordModel> recordModels = new ArrayList<>();

        for(int i = 0; i < numberOfRecords; i++) {
            RecordModel record = new RecordModel();

            record.setTitle("Record #" + i);
            record.setLenght("00:0"+i);

            recordModels.add(record);
        }

        return recordModels;
    }

}
