package com.example.bt_layout_2001181020_nguyenchanhbao.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Customer;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.HelperFunc;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn, registerBtn, facebookBtn, googleBtn;
    EditText userTxt, passTxt;
    SharedPreferences prefs;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Drawable background = getResources().getDrawable(R.drawable.loginbg, null);

        super.onCreate(savedInstanceState);
        new HelperFunc().changeBackgroundLayer(this, background, "#50000000");
        setContentView(R.layout.activity_login);

        Utils.context = this;
        Customer internalCustomer = Utils.LoadCustomerInternal();

        prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String username = prefs.getString("username", null);
        String password = prefs.getString("password", null);

        if (username != null && password != null) {
            if (username.equals(internalCustomer.getUsername()) && password.equals(internalCustomer.getPassword())) {
                Customer customer = new Customer(
                        "Nguyen Chanh Bao",
                        username,
                        internalCustomer.getEmail(),
                        password
                );

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("info", customer);

                startActivity(intent);
                finish();
            }
        }


        // Luon luon de duoi setContentView
        // O day thi view moi duoc khoi tao
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        facebookBtn = findViewById(R.id.facebookBtn);
        googleBtn = findViewById(R.id.googleBtn);

        userTxt = findViewById(R.id.userLoginTxt);
        passTxt = findViewById(R.id.passLoginTxt);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userTxt.getText().toString().isEmpty()) {
                    userTxt.setError("User can't be empty");
                    return;
                }

                if (passTxt.getText().toString().isEmpty()) {
                    passTxt.setError("Password can't be empty");
                    return;
                }

                final Dialog dialog = new Dialog(LoginActivity.this);

                if (passTxt.getText().toString().length() < 6) {
                    passTxt.setError("Minimum 6 numbers");
                    return;
                }

                if (internalCustomer == null ||
                        !userTxt.getText().toString().equals(internalCustomer.getUsername()) ||
                        !passTxt.getText().toString().equals(internalCustomer.getPassword())
                        ) {
                    Toast.makeText(LoginActivity.this, "User's not valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent regisIntent = getIntent();
                customer = (Customer) regisIntent.getSerializableExtra("info");

                if (customer == null) {
                    customer = new Customer(
                            "Nguyen Chanh Bao",
                            userTxt.getText().toString(),
                            internalCustomer.getEmail(),
                            passTxt.getText().toString());
                }

                prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("username", userTxt.getText().toString());
                editor.putString("password", passTxt.getText().toString());
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("info", customer);

                startActivity(intent);
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 100);
                finish();
            }
        });

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.facebook.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}