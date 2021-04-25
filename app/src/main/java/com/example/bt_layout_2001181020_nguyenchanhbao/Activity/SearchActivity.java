package com.example.bt_layout_2001181020_nguyenchanhbao.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.SearchView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.APIHelper.APIHelper;
import com.example.bt_layout_2001181020_nguyenchanhbao.Adapter.FurnitureAdapter;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Categories;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;

    List<Furniture> arrayList;
    RecyclerView listView;

    FurnitureAdapter furnitureAdapter;
    TagGroup mTagGroup;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Utils.context = this;

        arrayList = new ArrayList<>();
        listView = findViewById(R.id.listView);

        furnitureAdapter = new FurnitureAdapter(SearchActivity.this, arrayList);
        LinearLayoutManager linear = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linear);
        listView.setAdapter(furnitureAdapter);

        searchView = findViewById(R.id.search_vew);
        searchView.setIconifiedByDefault(true);

        searchView.setFocusable(true);
        searchView.setIconified(false);

        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
//                furnitureAdapter.clear();
                APIHelper.fetchFurniture(furnitureAdapter, newText);

                if (newText.isEmpty()) {
                    listView.setVisibility(View.GONE);
                    return false;
                }

                listView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mTagGroup = findViewById(R.id.tag_group);

//        mTagGroup.setTags(Categories.getMockData().stream().map(item -> item.getName()).collect(Collectors.toList()));

        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });
    }

//    private void searchFurniture(String newText) {
//        ArrayList<Furniture> tmp = new ArrayList<>();
//
//        for (Furniture furniture : Utils.LoadFileInternal()){
//
//            if (furniture.getName().toLowerCase().contains(newText.toLowerCase())){
//
//                tmp.add(furniture);
//            }
//        }
//
////        Toast.makeText(this, tmp.size()+"", Toast.LENGTH_SHORT).show();
//
//        if (tmp.size() > 0){
//            furnitureAdapter.
//            furnitureAdapter.addAll(tmp);
//
//            furnitureAdapter.notifyDataSetChanged();
//            listView.setVisibility(View.VISIBLE);
//        }
//
//        if (newText.isEmpty()){
//            listView.setVisibility(View.GONE);
//        }
//    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}