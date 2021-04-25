package com.example.bt_layout_2001181020_nguyenchanhbao.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.Activity.LoginActivity;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Customer;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;
import com.google.android.material.appbar.AppBarLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AccountFragment extends Fragment {
    private Customer customer;
    EditText userInfo, nameInfo, emailInfo, passInfo;
    Button logoutBtn;
    RadioGroup sexChoice;
    AppBarLayout appBarLayout;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.customer = (Customer) getArguments().getSerializable("info");


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userInfo = (EditText) view.findViewById(R.id.userTxt);
        nameInfo = (EditText) view.findViewById(R.id.nameTxt);

        emailInfo = (EditText) view.findViewById(R.id.emailInfoTxt);
        passInfo = (EditText) view.findViewById(R.id.infoPassword);

        logoutBtn = (Button)view.findViewById(R.id.logoutBtn);
        sexChoice = (RadioGroup)view.findViewById(R.id.radioGroup2);

        sexChoice.check(R.id.radioButton4);

        userInfo.setText(customer.getName());
        nameInfo.setText(customer.getUsername());
        emailInfo.setText(customer.getEmail());
        passInfo.setText(customer.getPassword());

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ACCOUNT INFO");

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE).edit();
                editor.clear().apply();

                Utils.furnitureHistory = new ArrayList<>();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_option, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuSaveOption) {
            this.customer = (Customer) getArguments().getSerializable("info");

            customer.setName(userInfo.getText().toString());
            customer.setUsername(nameInfo.getText().toString());
            customer.setEmail(emailInfo.getText().toString());
            customer.setPassword(passInfo.getText().toString());

            writeCustomerToInternal(customer);

            Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();

            SharedPreferences.Editor editor = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE).edit();
            editor.clear().apply();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void writeCustomerToInternal(Customer customer){
        try {
            File file = new File(getContext().getFilesDir(), "Customer");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new
                    ObjectOutputStream(fileOutputStream);

//      if (LoadFileInternal() == null) objectOutputStream.writeObject(furnitureHistory);

            objectOutputStream.writeObject(customer);

            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("DONEs");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}