package com.example.bt_layout_2001181020_nguyenchanhbao.Interface;

import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Categories;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FurnituresAPI {
    @GET("furnitures")
    Call<List<Furniture>> get();

    @GET("furnitures/{name}")
    Call<List<Furniture>> get(@Path("name") String name);

    @GET("categories/{id}")
    Call<List<Furniture>> get(@Path("id") int id);
}
