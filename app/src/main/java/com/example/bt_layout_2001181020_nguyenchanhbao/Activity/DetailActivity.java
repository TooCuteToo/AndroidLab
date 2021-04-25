package com.example.bt_layout_2001181020_nguyenchanhbao.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

public class DetailActivity extends AppCompatActivity {
    private ImageView detailImage;
    private TextView detailDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        detailImage = findViewById(R.id.detailImage);
        detailDesc = findViewById(R.id.detailDesc);

        Furniture tmp = (Furniture) intent.getSerializableExtra("furniture");

//        detailImage.setImageResource(tmp.getImage());
        Glide.with(DetailActivity.this).load("file:///android_asset/" + tmp.getImage()).into(detailImage);
        detailDesc.setText(tmp.getDescription());

        getSupportActionBar().setTitle(tmp.getName());
        getSupportActionBar().show();
    }
}