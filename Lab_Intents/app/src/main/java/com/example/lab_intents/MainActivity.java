package com.example.lab_intents;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_INPUT_MESSAGE = 0;
    private static final int REQUEST_PICK_IMAGE = 1;

    public static final String EXTRA_MESSAGE = "mk.ukim.finki.mpip.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        findViewById(R.id.btn_explicit).setOnClickListener(this::clickExplicit);
        findViewById(R.id.btn_implicit).setOnClickListener(this::clickImplicit);
        findViewById(R.id.btn_send_message).setOnClickListener(this::clickSendMessage);
        findViewById(R.id.btn_view_image).setOnClickListener(this::clickViewImage);
    }

    public void clickExplicit(View view){
        Intent startExplicit = new Intent(this, ExplicitActivity.class);
        startActivityForResult(startExplicit, REQUEST_INPUT_MESSAGE);
    }

    public void clickImplicit(View view){
        Intent startImplicit = new Intent(ImplicitActivity.ACTION_IMPLICIT);
        startActivity(startImplicit);
    }

    public void clickSendMessage(View view){
        Intent sendIntent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title")
                .putExtra(Intent.EXTRA_TEXT, "Content sent from MainActivity");

        if(sendIntent.resolveActivity(getPackageManager()) != null)
            startActivity(sendIntent);
        else
            Toast.makeText(this, "No suitable app available at the moment", Toast.LENGTH_LONG).show();
    }

    public void clickViewImage(View view){
        Intent pickImage = new Intent(Intent.ACTION_PICK)
                .setType("image/*");

        if(pickImage.resolveActivity(getPackageManager()) != null)
            startActivityForResult(pickImage, REQUEST_PICK_IMAGE);
        else
            Toast.makeText(this, "No suitable app available at the moment", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUEST_INPUT_MESSAGE && data != null){
            String message = data.getStringExtra(EXTRA_MESSAGE);
            ((TextView) findViewById(R.id.txt_explicit)).setText(message);
        }
        else if(resultCode == RESULT_OK && requestCode == REQUEST_PICK_IMAGE && data != null){
            showImage(data.getData());
        }
    }

    private void showImage(Uri imageUri) {
        Intent showImage = new Intent(Intent.ACTION_VIEW, imageUri);
        if(showImage.resolveActivity(getPackageManager()) != null)
            startActivity(showImage);
        else
            Toast.makeText(this, "No suitable app available at the moment", Toast.LENGTH_LONG).show();
    }

}