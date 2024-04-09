package com.example.homepage;

        import android.annotation.SuppressLint;
        import android.app.AlertDialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.PorterDuff;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;
        import android.widget.ScrollView;
        import android.widget.Toast;
        import android.widget.VideoView;

        import androidx.appcompat.app.AppCompatActivity;

        public class MainActivity extends AppCompatActivity {

        private ScrollView scrollView;
        private VideoView videoView;
        private LinearLayout linearLayout;
        private ImageButton btnAccount, btnLike, btnComment, btnMusic, btnShare, btnDownload;
        private boolean isLiked = false;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        linearLayout = findViewById(R.id.linearLayout);
        Button btnCreate = findViewById(R.id.btnCreate);
        btnAccount = findViewById(R.id.btnAccount);
        btnLike = findViewById(R.id.btnLike);
        btnComment = findViewById(R.id.btnComment);
        btnMusic = findViewById(R.id.btnMusic);
        btnShare = findViewById(R.id.btnShare);
        btnDownload = findViewById(R.id.btndownload);
        videoView = findViewById(R.id.videoView);

        btnCreate.setOnClickListener(v -> {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        }
        });
            btnAccount.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            });

        btnLike.setOnClickListener(v -> {
            if (!isLiked) {
                btnLike.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                isLiked = true;
            } else {
                btnLike.clearColorFilter();
                isLiked = false;
            }
        });
        btnComment.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText input = new EditText(MainActivity.this);
            builder.setView(input);
            builder.setPositiveButton("OK", (dialog, which) -> {
                String comment = input.getText().toString();
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        });

        btnMusic.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Music is playing!", Toast.LENGTH_SHORT).show();
        });

        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Sharing some content");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        btnDownload.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "Video is downloading!", Toast.LENGTH_SHORT).show();
        });
        int[] videoFiles = {R.raw.video, R.raw.video4, R.raw.video3};

        for (int videoFile : videoFiles) {
            VideoView videoView = new VideoView(this);
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoFile);
            videoView.setVideoURI(videoUri);
            videoView.setOnPreparedListener(MediaPlayer::start);
            videoView.setOnErrorListener((mp, what, extra) -> {
                Toast.makeText(MainActivity.this, "Error playing video!", Toast.LENGTH_SHORT).show();
                return true;
            });
            linearLayout.addView(videoView);
        }
    }}