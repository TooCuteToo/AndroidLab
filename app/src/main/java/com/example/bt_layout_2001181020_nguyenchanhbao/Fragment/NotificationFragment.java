package com.example.bt_layout_2001181020_nguyenchanhbao.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.Adapter.FurnitureAdapter;
import com.example.bt_layout_2001181020_nguyenchanhbao.Activity.DetailActivity;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

  RecyclerView listView;
  ArrayList<Furniture> arrayList;
  FurnitureAdapter furnitureAdapter;
  public NotificationFragment() {
    // Required empty public constructor
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_notification, container, false);
  }
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    LinearLayoutManager linear = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    listView = view.findViewById(R.id.notification_listView);
    listView.setLayoutManager(linear);

    ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

    arrayList = Utils.getFurnitureHistory();

//    if (arrayList == null || arrayList.size() == 0) arrayList = Utils.getFurnitureHistory();
    Toast.makeText(getContext(), String.valueOf(arrayList.size()), Toast.LENGTH_SHORT).show();
    furnitureAdapter = new FurnitureAdapter(getContext(), arrayList);
    listView.setAdapter(furnitureAdapter);
  }
}