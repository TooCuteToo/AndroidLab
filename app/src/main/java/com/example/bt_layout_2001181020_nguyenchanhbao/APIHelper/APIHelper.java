package com.example.bt_layout_2001181020_nguyenchanhbao.APIHelper;

import android.view.View;

import com.example.bt_layout_2001181020_nguyenchanhbao.Adapter.FurnitureAdapter;
import com.example.bt_layout_2001181020_nguyenchanhbao.Adapter.FurnitureAdapterGrid;
import com.example.bt_layout_2001181020_nguyenchanhbao.Interface.CategoriesAPI;
import com.example.bt_layout_2001181020_nguyenchanhbao.Interface.FurnituresAPI;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Categories;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    public static void fetchFurnitures(FurnitureAdapter furnitureAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FurnituresAPI furnituresAPI = retrofit.create(FurnituresAPI.class);

        Call<List<Furniture>> call = furnituresAPI.get();

        call.enqueue(new Callback<List<Furniture>>() {
            @Override
            public void onResponse(Call<List<Furniture>> call, Response<List<Furniture>> response) {
                if (response.isSuccessful()) {
                    List<Furniture> furnitures = response.body();
                    furnitureAdapter.setArrayList(furnitures);
                    furnitureAdapter.notifyDataSetChanged();
                }

                else return;
            }

            @Override
            public void onFailure(Call<List<Furniture>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public static void fetchFurniture(FurnitureAdapter furnitureAdapter, String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FurnituresAPI furnituresAPI = retrofit.create(FurnituresAPI.class);

        Call<List<Furniture>> call = furnituresAPI.get(name);

        call.enqueue(new Callback<List<Furniture>>() {
            @Override
            public void onResponse(Call<List<Furniture>> call, Response<List<Furniture>> response) {
                if (response.isSuccessful()) {
                    List<Furniture> furnitures = response.body();
                    furnitureAdapter.setArrayList(furnitures);
                    furnitureAdapter.notifyDataSetChanged();
                }

                else return;
            }

            @Override
            public void onFailure(Call<List<Furniture>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public static void fetchCategories(FurnitureAdapterGrid furnitureGridAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriesAPI categoriesAPI = retrofit.create(CategoriesAPI.class);

        Call<List<Categories>> call = categoriesAPI.get();

        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if (response.isSuccessful()) {
                    List<Categories> categories = response.body();
                    furnitureGridAdapter.setArrayList(categories);
                    furnitureGridAdapter.notifyDataSetChanged();
                    System.out.println(categories);
                }

                else return;
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public static void fetchFurnitureByCate(FurnitureAdapter furnitureAdapter, int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FurnituresAPI furnituresAPI = retrofit.create(FurnituresAPI.class);

        Call<List<Furniture>> call = furnituresAPI.get(id);

        call.enqueue(new Callback<List<Furniture>>() {
            @Override
            public void onResponse(Call<List<Furniture>> call, Response<List<Furniture>> response) {
                if (response.isSuccessful()) {
                    List<Furniture> furnitures = response.body();
                    furnitureAdapter.setArrayList(furnitures);
                    furnitureAdapter.notifyDataSetChanged();
                }

                else return;
            }

            @Override
            public void onFailure(Call<List<Furniture>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}
