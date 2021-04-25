package com.example.bt_layout_2001181020_nguyenchanhbao.Interface;

import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Categories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesAPI {
    @GET("categories")
    Call<List<Categories>> get();
}
