package com.example.mobsys07;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;

public class FragmentPhoto extends Fragment {

    private ImageView imageView;
    private Button photoBtn;
    private Uri imageUrl;

    private final ActivityResultLauncher<Uri> contract = registerForActivityResult(
            new ActivityResultContracts.TakePicture(),
            result -> {
                if (result) {
                    imageView.setImageURI(null);
                    imageView.setImageURI(imageUrl);
                    saveImageUri(imageUrl);
                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageView = view.findViewById(R.id.imageView);
        photoBtn = view.findViewById(R.id.photoBtn);

        Uri savedImageUri = loadImageUri();
        if (savedImageUri != null) {
            imageView.setImageURI(savedImageUri);
            imageUrl = savedImageUri;
        } else {
            imageUrl = createImageUri();
        }

        photoBtn.setOnClickListener(v -> contract.launch(imageUrl));

        return view;
    }

    private Uri createImageUri() {
        File image = new File(requireContext().getFilesDir(), "camera_photos.png");

        return FileProvider.getUriForFile(
                getContext(),
                "com.example.mob07.FileProvider",
                image
        );
    }

    private void saveImageUri(Uri uri) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("saved_image_uri", uri.toString());
        editor.apply();
    }

    private Uri loadImageUri() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String uriString = sharedPreferences.getString("saved_image_uri", null);
        return uriString != null ? Uri.parse(uriString) : null;
    }


}