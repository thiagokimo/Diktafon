package io.kimo.diktafon.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.kimo.diktafon.presentation.model.RecordModel;

/**
 * Maps local files into RecordModels
 */
public class RecordMapper {

    public static RecordModel toModel(File file) {
        RecordModel recordModel = new RecordModel();

        recordModel.setTitle(file.getName());

        return recordModel;
    }

    public static List<RecordModel> toModels(List<File> files) {
        List<RecordModel> recordModels = new ArrayList<>();

        for(File file : files) {
            recordModels.add(toModel(file));
        }

        return recordModels;
    }

}
