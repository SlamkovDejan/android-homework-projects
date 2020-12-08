package com.example.lab_intents;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.stream.Collectors;

public class ImplicitActivity extends AppCompatActivity {

    public static final String ACTION_IMPLICIT = "mk.ukim.finki.mpip.IMPLICIT_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        Intent main = new Intent(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER);

        List<String> classes = getPackageManager().queryIntentActivities(main, 0).stream()
                .map(ri -> ri.activityInfo.name)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classes);
        ((ListView) findViewById(R.id.list_view_implicit)).setAdapter(adapter);
    }

}