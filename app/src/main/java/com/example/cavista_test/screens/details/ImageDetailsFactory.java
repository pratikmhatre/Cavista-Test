package com.example.cavista_test.screens.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class ImageDetailsFactory implements ViewModelProvider.Factory {
    private ImageDetailsRepository repository;

    @Inject
    public ImageDetailsFactory(ImageDetailsRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ImageDetailsViewModel.class)) {
            return (T) new ImageDetailsViewModel(repository);
        }
        throw new IllegalStateException();
    }
}
