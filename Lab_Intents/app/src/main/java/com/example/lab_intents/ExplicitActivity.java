package com.example.lab_intents;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ExplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        init();
    }

    private void init() {
        findViewById(R.id.btn_ok).setOnClickListener(this::clickOK);
        findViewById(R.id.btn_cancel).setOnClickListener(this::clickCancel);
    }

    public void clickOK(View view){
        Intent data = new Intent();
        String message = ((EditText) findViewById(R.id.edit_text_message)).getText().toString();
        data.putExtra(MainActivity.EXTRA_MESSAGE, message);
        setResult(RESULT_OK, data);
        finish();
    }

    public void clickCancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

}