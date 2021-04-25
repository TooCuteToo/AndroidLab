package com.example.bt_layout_2001181020_nguyenchanhbao.Module;

import com.example.bt_layout_2001181020_nguyenchanhbao.R;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable {
  @SerializedName("categoryId")
  private int id;

  @SerializedName("name")
  private String name;

  @SerializedName("image")
  private String image;

  public Categories() {
  }

  public Categories(int id, String name, String image) {
    this.id = id;
    this.name = name;
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

//  public static ArrayList<Categories> getMockData(){
//    categories = new ArrayList<>();
//
//    categories.add(new Categories(1, "Bed Room", R.drawable.bed_room));
//    categories.add(new Categories(2, "Living Room", R.drawable.living_room));
//    categories.add(new Categories(3, "Meeting Room", R.drawable.meeting_room));
//    categories.add(new Categories(4, "Accessories", R.drawable.accessories));
//
//    return categories;
//  }
//
//  public static Categories getCategory(int index) {
//    return categories.get(index);
//  }
}
