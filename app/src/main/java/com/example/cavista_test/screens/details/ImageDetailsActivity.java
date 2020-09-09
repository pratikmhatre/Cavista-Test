package com.example.cavista_test.screens.details;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.cavista_test.application.MyApplication;
import com.example.cavista_test.data.network.responses.ImagesResponse;
import com.example.cavista_test.databinding.ActivityImageDetailsBinding;
import com.example.cavista_test.di.components.ActivityComponent;
import com.example.cavista_test.di.components.DaggerActivityComponent;
import com.example.cavista_test.di.modules.ActivityModule;
import com.google.gson.Gson;

import javax.inject.Inject;

public class ImageDetailsActivity extends AppCompatActivity {
    @Inject
    ImageDetailsViewModel detailsViewModel;
    private ActivityImageDetailsBinding detailsBinding;
    private ImagesResponse.Data.Image imageData;

    @Inject
    CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityComponent activityComponent = DaggerActivityComponent.builder().appComponent(((MyApplication) getApplicationContext()).getAppComponent()).activityModule(new ActivityModule(this)).build();
        activityComponent.inject(this);
        detailsBinding = ActivityImageDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());

        //receive data from previous activity
        String imageDataString = getIntent().getStringExtra("image");
        imageData = new Gson().fromJson(imageDataString, ImagesResponse.Data.Image.class);

        detailsViewModel.setImageData(imageData);
        Glide.with(this).load(imageData.getLink()).into(detailsBinding.ivImage);


        //setup toolbar
        detailsBinding.toolbar.setTitleTextColor(Color.WHITE);
        detailsBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailsBinding.toolbar.setTitle(imageData.getTitle());


        //setup recyclerview for comments
        detailsBinding.recyclerComments.setAdapter(commentsAdapter);
        detailsBinding.recyclerComments.setLayoutManager(new LinearLayoutManager(this));
        detailsBinding.recyclerComments.setNestedScrollingEnabled(false);

        //observe db data
        detailsViewModel.getImageComments().observe(this, imageComments -> commentsAdapter.addComments(imageComments));

        detailsBinding.btnPostComment.setOnClickListener(view -> {
            postComment();
            detailsBinding.edComment.setText("");
        });

    }

    private void postComment() {
        String comment = detailsBinding.edComment.getText().toString();
        if (comment.trim().isEmpty()) {
            Toast.makeText(this, "Please enter comment", Toast.LENGTH_SHORT).show();
            return;
        }
        detailsViewModel.addComment(comment);
    }
}
