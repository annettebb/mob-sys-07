package com.example.mobsys07;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {

    private final List<Audio> audioList;
    private final OnAudioClickListener listener;

    public interface OnAudioClickListener {
        void onAudioClick(int position);
    }

    public AudioAdapter(List<Audio> audioList, OnAudioClickListener listener) {
        this.audioList = audioList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        Audio audio = audioList.get(position);
        holder.songName.setText(audio.getName());
        holder.songAuthor.setText(audio.getAuthor());
        holder.itemView.setOnClickListener(v -> listener.onAudioClick(position));
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public static class AudioViewHolder extends RecyclerView.ViewHolder {
        TextView songName, songAuthor;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.songName);
            songAuthor = itemView.findViewById(R.id.songAuthor);
        }
    }
}
