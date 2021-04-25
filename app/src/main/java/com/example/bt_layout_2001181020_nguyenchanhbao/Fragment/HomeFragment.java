package com.example.bt_layout_2001181020_nguyenchanhbao.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.Adapter.FurnitureAdapter;
import com.example.bt_layout_2001181020_nguyenchanhbao.Activity.DetailActivity;
import com.example.bt_layout_2001181020_nguyenchanhbao.APIHelper.APIHelper;
import com.example.bt_layout_2001181020_nguyenchanhbao.Interface.FurnituresAPI;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Categories;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
  RecyclerView listView;
  List<Furniture> arrayList;
  ArrayList<Categories> categoriesArrayList;
  FurnitureAdapter furnitureAdapter;
  int pos;
  Furniture furniture;

  public HomeFragment() {
    // Required empty public constructor
    this.pos = -1;
  }

  public HomeFragment(int pos) {
    this.pos = pos;

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_home, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    listView = view.findViewById(R.id.home_listView);
    ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


    arrayList = new ArrayList<>();
    LinearLayoutManager linear = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    GridLayoutManager grid = new GridLayoutManager(getContext(), 1);
    listView.setLayoutManager(grid);
    furnitureAdapter = new FurnitureAdapter(getContext(), arrayList);
    listView.setAdapter(furnitureAdapter);

    if (pos == -1) APIHelper.fetchFurnitures(furnitureAdapter);
    else APIHelper.fetchFurnitureByCate(furnitureAdapter, pos);

    registerForContextMenu(listView);
  }

  @Override
  public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
   if (v.getId() == R.id.listView) {
      getActivity().getMenuInflater().inflate(R.menu.popup_menu,menu);
   }

    getActivity().getMenuInflater().inflate(R.menu.popup_menu,menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.delete) {
      Toast.makeText(getContext(), "Delete success" + furnitureAdapter.postion, Toast.LENGTH_SHORT).show();
      furnitureAdapter.getArrayList().remove(furnitureAdapter.postion);
      furnitureAdapter.notifyDataSetChanged();
    }

    return super.onContextItemSelected(item);
  }
}