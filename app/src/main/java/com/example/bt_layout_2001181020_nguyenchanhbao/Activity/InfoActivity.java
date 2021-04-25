package com.example.bt_layout_2001181020_nguyenchanhbao.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Customer;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

public class InfoActivity extends AppCompatActivity {
    EditText name, username, email, password;
    Button logoutBtn;
    RadioGroup sexChoice;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = (EditText)findViewById(R.id.nameTxt);
        email = (EditText)findViewById(R.id.emailInfoTxt);
        username = (EditText)findViewById(R.id.userTxt);
        password = (EditText)findViewById(R.id.infoPassword);

        logoutBtn = (Button)findViewById(R.id.logoutBtn);
        sexChoice = (RadioGroup)findViewById(R.id.radioGroup2);

        sexChoice.check(R.id.radioButton3);

        getSupportActionBar().setTitle("ACCOUNT INFO");

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("info");

        name.setText(customer.getName());
        username.setText(customer.getUsername());
        password.setText(customer.getPassword());
        email.setText(customer.getEmail());

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                editor.clear().apply();

                Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_option, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


}