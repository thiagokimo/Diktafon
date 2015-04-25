package io.kimo.diktafon.presentation.presenter;

import android.content.Context;

import com.nanotasks.Completion;
import com.nanotasks.Tasks;

import java.util.List;

import io.kimo.diktafon.domain.GetLocalRecordings;
import io.kimo.diktafon.presentation.model.RecordModel;
import io.kimo.diktafon.presentation.view.RecordListView;

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

        Tasks.executeInBackground(context, new GetLocalRecordings(), new Completion<List<RecordModel>>() {
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
}
