package com.example.mobsys07;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentVideo extends Fragment {

    private VideoView videoView;
    private Button playBtn, stopBtn, nextBtn, prevBtn;

    private RecyclerView videoRecyclerView;

    private int currentVideoIndex = 0;

    private final List<Video> videos = new ArrayList<>();

    private Boolean isVideoPlay = false;
    private Boolean isPaused = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (videos.isEmpty()) {
            videos.add(new Video("Ем грибы :)", "Valera Ghosther", R.raw.video_1));
            // Нужные видео были слишком большие, поэтому были удалены!
            // videos.add(new Video("«КОРОЛЕВСТВО ШИПОВ И РОЗ» — УНЫЛОЕ ФЭНТЕЗИ С САМЫМ ТУПЫМ СЮЖЕТОМ | Почему это любят?", "Anthony Uly", R.raw.video_2));
            // videos.add(new Video("Миллениалы и зумеры: вся правда о теории поколений", "Anthony Uly", R.raw.video_3));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        videoView = view.findViewById(R.id.videoView);

        playBtn = view.findViewById(R.id.playBtn);
        nextBtn = view.findViewById(R.id.nextBtn);
        prevBtn = view.findViewById(R.id.prevBtn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickPlayBtn();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextVideo();
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevVideo();
            }
        });

        videoRecyclerView = view.findViewById(R.id.videoRecyclerView);

        VideoAdapter videoAdapter = new VideoAdapter(videos, this::playSelectedVideo);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoRecyclerView.setAdapter(videoAdapter);

        return view;
    }

    private void handleClickPlayBtn() {
        if (isVideoPlay) {
            pauseVideo();
        } else {
            if (isPaused) {
                videoView.start();
                playBtn.setText(R.string.audio_pause_btn);
                isPaused = false;
                isVideoPlay = true;
            } else {
                playVideo();
            }
        }
    }

    private void playSelectedVideo(int position) {
        currentVideoIndex = position;
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
        isVideoPlay = false;
        playVideo();
    }

    private void playVideo() {
        if (!isVideoPlay) {
            Uri videoUri = Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + videos.get(currentVideoIndex).getFile());
            videoView.setVideoURI(videoUri);

            videoView.setOnPreparedListener(mp -> {
                videoView.start();
                playBtn.setText(R.string.audio_pause_btn);
                isVideoPlay = true;
                isPaused = false;
            });

            videoView.setOnCompletionListener(mp -> nextVideo());
        }
    }

    private void pauseVideo() {
        if (videoView.isPlaying()) {
            videoView.pause();
            playBtn.setText(R.string.audio_play_btn);
            isVideoPlay = false;
            isPaused = true;
        }
    }

    private void nextVideo() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
        isVideoPlay = false;
        currentVideoIndex = (currentVideoIndex + 1) % videos.size();
        playVideo();
    }

    private void prevVideo() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
        isVideoPlay = false;
        currentVideoIndex = (currentVideoIndex - 1 + videos.size()) % videos.size();
        playVideo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }

    public void onPause() {
        super.onPause();

        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }

        isVideoPlay = false;
        isPaused = false;
        playBtn.setText(R.string.audio_play_btn);
    }
}