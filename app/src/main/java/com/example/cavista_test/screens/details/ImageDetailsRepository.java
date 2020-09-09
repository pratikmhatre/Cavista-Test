package com.example.cavista_test.screens.details;

import androidx.lifecycle.LiveData;
import com.example.cavista_test.data.DataManager;
import com.example.cavista_test.data.db.ImageComment;
import java.util.List;
import javax.inject.Inject;

public class ImageDetailsRepository {
    DataManager dataManager;
    @Inject
    public ImageDetailsRepository(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void insertComment(ImageComment comment) {
        dataManager.insertComment(comment);
    }

    LiveData<List<ImageComment>> getCommentOfImage(String imageId) {
        return dataManager.getCommentsOfImage(imageId);
    }
}
