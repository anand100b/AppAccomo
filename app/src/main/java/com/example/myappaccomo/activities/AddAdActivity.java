package com.example.myappaccomo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myappaccomo.R;
import com.example.myappaccomo.entities.Ad;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class AddAdActivity extends AppCompatActivity {

    EditText categoryEditText;
    EditText typeEditText;
    EditText descriptionEditText;
    Button postButton;
    Ad ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        categoryEditText = findViewById(R.id.categoryEditText);
        typeEditText = findViewById(R.id.typeEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        postButton = findViewById(R.id.postButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post(v);

            }
        });

    }

    private void post(View v) {
        String categoryName = categoryEditText.getText().toString();
        String typeName = typeEditText.getText().toString();
        String adDescription = descriptionEditText.getText().toString();
        if(categoryName.isEmpty()){
            Snackbar.make(v, "mention the category please", Snackbar.LENGTH_SHORT).show();
            return;
        }

        ad = new Ad();
        ad.setCategory(categoryName);
        ad.setType(typeName);
        ad.setDescription(adDescription);

        Intent goingBack = new Intent();
        goingBack.putExtra(ad.AD_KEY, ad);
        setResult(RESULT_OK, goingBack);
        finish();
    }
}

