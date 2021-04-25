package com.example.bt_layout_2001181020_nguyenchanhbao.Module;

import com.example.bt_layout_2001181020_nguyenchanhbao.R;

public class Constant {
  public static String databaseName = "FurnitureDB.db";

  public static final int[] IMG_ARRAY = new int[] {
          R.drawable.hinh_1, R.drawable.hinh_2,
          R.drawable.hinh_3, R.drawable.hinh_4,
          R.drawable.hinh_5, R.drawable.hinh_6,
  };

  public static final String[] TITLE_ARRAY = new String[] {
          Utils.context.getString(R.string.name_product_one),
          Utils.context.getString(R.string.name_product_two),
          Utils.context.getString(R.string.name_product_three),
          Utils.context.getString(R.string.name_product_four),
          Utils.context.getString(R.string.name_product_five),
  };

  public static final String[] DESC_TEMP = new String[] {
          Utils.context.getString(R.string.product_one),
          Utils.context.getString(R.string.product_two),
          Utils.context.getString(R.string.product_three),
          Utils.context.getString(R.string.product_four),
          Utils.context.getString(R.string.product_five),
  };
}
