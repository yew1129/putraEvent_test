package com.example.myapptest;

import static com.google.common.io.Files.getFileExtension;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UploadImage extends AppCompatActivity {
    private static final String TAG = "UploadImage";
    private final StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        MaterialButton choose_btn = findViewById(R.id.choose_btn);
        MaterialButton upload = findViewById(R.id.upload_btn);
        imageView = findViewById(R.id.image_view);

        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (imageUri != null) {
                   StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(String.valueOf(imageUri)));
                   fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                       Toast.makeText(UploadImage.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                   }).addOnFailureListener(e -> {
                       Toast.makeText(UploadImage.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                   });
               }
               else {
                   Toast.makeText(UploadImage.this, "Please select an image", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}