package com.example.bt_layout_2001181020_nguyenchanhbao.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.bt_layout_2001181020_nguyenchanhbao.APIHelper.APIHelper;
import com.example.bt_layout_2001181020_nguyenchanhbao.Adapter.FurnitureAdapterGrid;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Categories;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {
  RecyclerView gridView;
  ArrayList<Categories> arrayList;
  FurnitureAdapterGrid furnitureAdapterGrid;

  public DashboardFragment() {
    // Required empty public constructor
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_dashboard, container, false);
  }
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    gridView = view.findViewById(R.id.gridView);


//    arrayList = Categories.getMockData();
//    arrayList = db.getALLCategories();
    arrayList = new ArrayList<>();
    furnitureAdapterGrid = new FurnitureAdapterGrid(getContext(), arrayList);
    GridLayoutManager grid = new GridLayoutManager(getContext(), 2);

    gridView.setLayoutManager(grid);
    gridView.setAdapter(furnitureAdapterGrid);

    APIHelper.fetchCategories(furnitureAdapterGrid);

    ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

//    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> adapterView, View view, int i,
//                              long l) {
//        FragmentTransaction fragmentTransaction =
//                getFragmentManager().beginTransaction();
//
//        fragmentTransaction.replace(R.id.nav_host_fragment, new HomeFragment(i));
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//      }
//    });
  }
}