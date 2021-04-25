package com.example.bt_layout_2001181020_nguyenchanhbao.Module;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Furniture implements Serializable {
  @SerializedName("id")
  int id;

  @SerializedName("name")
  private String name;

  @SerializedName("description")
  private String description;

  @SerializedName("image")
  private String image;

  @SerializedName("categoryId")
  int categoryId;

  public Furniture() {}

  public Furniture(String name, String description, String image, int id, int categoryId) {
    this.name = name;
    this.description = description;
    this.image = image;
    this.id = id;
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
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

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

//  public static ArrayList<Furniture> getMockData(){
//    ArrayList<Furniture> tmpList = new ArrayList<>();
//
//    for (int i = 0; i < 2; ++i) {
//      for (int j = 0; j < 5; ++j) {
//        int index = new Random().nextInt(5);
//        Furniture temp = new Furniture(
//                Constant.TITLE_ARRAY[index],
//                Constant.DESC_TEMP[index],
//                Constant.IMG_ARRAY[new Random().nextInt(6)],
//                0, new Categories()
//        );
//
//        if (tmpList.indexOf(temp) < 0) tmpList.add(temp);
//      }
//    }
//
//    return tmpList;
//  }

//  public static ArrayList<Furniture> getRandomMockFurniture() {
//    ArrayList<Furniture> randomList = new ArrayList<>();
//    ArrayList<Furniture> fullList = Furniture.getMockData();
//
//    for (int i = 0; i < fullList.size(); ++i) {
//      Furniture tmp = fullList.get(new Random().nextInt(fullList.size()));
//      if (randomList.indexOf(tmp) >= 0) {
//        randomList.add(tmp);
//      }
//    }
//
//    return randomList;
//  }
}
