package io.kimo.diktafon.domain;

import android.os.Environment;

import com.nanotasks.BackgroundWork;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.kimo.diktafon.Diktafon;
import io.kimo.diktafon.presentation.model.RecordModel;
import io.kimo.diktafon.util.RecordMapper;

/**
 * Retrieve all local records
 */
public class GetLocalRecordings implements BackgroundWork<List<RecordModel>> {

    @Override
    public List<RecordModel> doInBackground() throws Exception {

        List<RecordModel> recordModels = new ArrayList<>();

        File appFolder = new File(Environment.getExternalStorageDirectory() + Diktafon.APP_FOLDER);
        recordModels.addAll(RecordMapper.toModels(Arrays.asList(appFolder.listFiles())));

        return recordModels;
    }
}
