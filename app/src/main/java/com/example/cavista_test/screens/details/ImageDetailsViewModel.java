package com.example.cavista_test.screens.details;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.cavista_test.data.db.ImageComment;
import com.example.cavista_test.data.network.responses.ImagesResponse;

import java.util.List;

public class ImageDetailsViewModel extends ViewModel {
    private ImageDetailsRepository repository;
    private ImagesResponse.Data.Image imageData;

    void setImageData(ImagesResponse.Data.Image imageData) {
        this.imageData = imageData;
    }

    public ImageDetailsViewModel(ImageDetailsRepository repository) {
        this.repository = repository;
    }

    void addComment(String comment) {
        //new comment added
        ImageComment imageComment = new ImageComment();
        imageComment.setImageId(imageData.getId());
        imageComment.setComment(comment);
        imageComment.setDateCreated(String.valueOf(System.currentTimeMillis()));

        //perform data insert action on worker thread
        Runnable runnable = () -> repository.insertComment(imageComment);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    LiveData<List<ImageComment>> getImageComments() {
        //fetch saved comments corresponding to image id
        return repository.getCommentOfImage(imageData.getId());
    }
}
