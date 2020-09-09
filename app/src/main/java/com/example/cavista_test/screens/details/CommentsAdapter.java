package com.example.cavista_test.screens.details;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cavista_test.data.db.ImageComment;
import com.example.cavista_test.databinding.ItemCommentBinding;
import com.example.cavista_test.utils.CommentDiffCallbacks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentHolder> {
    private ArrayList<ImageComment> commentsList = new ArrayList<>();


    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding rootBinding = ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommentHolder(rootBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.bindComment(commentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        private ItemCommentBinding commentBinding;

        public CommentHolder(ItemCommentBinding commentBinding) {
            super(commentBinding.getRoot());
            this.commentBinding = commentBinding;
        }

        void bindComment(ImageComment comment) {
            commentBinding.tvComment.setText(comment.getComment());
            commentBinding.tvDate.setText(getRelativeTime(comment.getDateCreated()));
        }
    }

    void addComments(List<ImageComment> arrayList) {
        CommentDiffCallbacks callbacks = new CommentDiffCallbacks(commentsList, arrayList);
        DiffUtil.DiffResult diff = DiffUtil.calculateDiff(callbacks);
        this.commentsList.clear();
        this.commentsList.addAll(arrayList);
        diff.dispatchUpdatesTo(this);
    }

    private CharSequence getRelativeTime(String millis) {
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(millis), Calendar.getInstance().getTimeInMillis(), 0);
    }
}
