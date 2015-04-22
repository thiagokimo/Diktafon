package io.kimo.diktafon.presentation.view;

import java.util.List;

import io.kimo.diktafon.presentation.model.RecordModel;

/**
 * RecordListView functionalities
 */
public interface RecordListView {

    void renderRecordList(List<RecordModel> records);
    void clearRecordList();

    void showList();
    void hideList();

    void showLoading();
    void hideLoading();

    void showEmpty(String feedback);
    void hideEmpty();

}
