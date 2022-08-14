package com.imadex.intentbasic;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startBtn);

        startButton.setOnClickListener(view -> {
            // Pass in the mime type you'd like to allow the user to select
            // as the input
            Toast.makeText(view.getContext(), "Activity Opened For Result", Toast.LENGTH_SHORT).show();
            mGetContent.launch("image/*");
        });

        findViewById(R.id.browserBtn).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.duckduckgo.com"));
            startActivity(intent);
        });

        findViewById(R.id.activityBtn).setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), secondaryActivity.class);
            startActivity(intent);
        });

    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri
                    //Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
                    ImageView imageView = (ImageView)findViewById(R.id.img);
                    imageView.setImageURI(uri);
                    imageView.setVisibility(View.VISIBLE);
                    ((TextView)findViewById(R.id.text1)).setText("Activity Returned Result as Expected");
                }
            });

}
