package com.example.bt_layout_2001181020_nguyenchanhbao.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Customer;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button signInBtn, cancelBtn;
    EditText emailTxt, usernameTxt, passwordTxt, comfirmPassTxt;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signInBtn = (Button)findViewById(R.id.signInBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);

        emailTxt = (EditText) findViewById(R.id.emailTxt);
        usernameTxt = (EditText) findViewById(R.id.userNameTxt);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt);
        comfirmPassTxt = (EditText) findViewById(R.id.comfirmTxt);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValid(emailTxt.getText().toString())) {
                    emailTxt.setError("Invalid email address!");
                    return;
                }

                if (emailTxt.getText().toString().isEmpty()) {
                    emailTxt.setError("Email can't be empty");
                    return;
                }

                if (usernameTxt.getText().toString().isEmpty()) {
                    usernameTxt.setError("User can't be empty");
                    return;
                }

                if (passwordTxt.getText().toString().isEmpty()) {
                    passwordTxt.setError("Password can't be empty");
                    return;
                }

                if (passwordTxt.getText().toString().length() < 6) {
                    passwordTxt.setError("Minimun 6 numbers");
                    return;
                }

                if (comfirmPassTxt.getText().toString().isEmpty()) {
                    comfirmPassTxt.setError("Comfirm password can't be empty");
                    return;
                }

                if (comfirmPassTxt.getText().toString().equals(passwordTxt.getText().toString())) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                    customer = new Customer(
                            "Nguyen Chanh Bao",
                            usernameTxt.getText().toString(),
                            emailTxt.getText().toString(),
                            passwordTxt.getText().toString()
                    );

                    SharedPreferences.Editor editor = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE).edit();
                    editor.clear().apply();

                    Utils.context = RegisterActivity.this;
                    Utils.writeCustomerToInternal(customer);

                    Toast.makeText(RegisterActivity.this, customer.getUsername() + " " + customer.getPassword(), Toast.LENGTH_LONG).show();
                    intent.putExtra("info", customer);

                    setResult(101, intent);

                    startActivity(intent);
                    finish();

                } else {
                    comfirmPassTxt.setError("Comfirm password doesn't equal password");
                    return;
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValid(String email) {
        String emailRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }
}