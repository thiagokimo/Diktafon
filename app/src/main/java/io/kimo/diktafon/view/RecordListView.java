package io.kimo.diktafon.view;

import java.util.List;

import io.kimo.diktafon.model.RecordModel;

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
