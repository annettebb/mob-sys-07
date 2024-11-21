package com.example.mobsys07;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<Video> videoList;
    private final OnVideoClickListener listener;

    public interface OnVideoClickListener {
        void onVideoClick(int position);
    }

    public VideoAdapter(List<Video> videoList, OnVideoClickListener listener) {
        this.videoList = videoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.songName.setText(video.getName());
        holder.songAuthor.setText(video.getAuthor());
        holder.itemView.setOnClickListener(v -> listener.onVideoClick(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView songName, songAuthor;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.songName);
            songAuthor = itemView.findViewById(R.id.songAuthor);
        }
    }

}
