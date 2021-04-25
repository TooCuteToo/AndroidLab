package com.example.bt_layout_2001181020_nguyenchanhbao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_layout_2001181020_nguyenchanhbao.Activity.DetailActivity;
import com.example.bt_layout_2001181020_nguyenchanhbao.Fragment.HomeFragment;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Categories;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.util.List;

public class FurnitureAdapterGrid extends RecyclerView.Adapter<FurnitureAdapterGrid.CategoriesAdapterVH>  {
  private Context context;
  private List<Categories> arrayList;

  public FurnitureAdapterGrid(Context context, List<Categories> arrayList) {
    this.context = context;
    this.arrayList = arrayList;
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public List<Categories> getArrayList() {
    return arrayList;
  }

  public void setArrayList(List<Categories> arrayList) {
    this.arrayList = arrayList;
  }

  @NonNull
  @Override
  public CategoriesAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.layout_cell, parent, false);

    return new CategoriesAdapterVH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CategoriesAdapterVH holder, int position) {
    Categories Categories = arrayList.get(position);

    holder.cell_txt.setText(Categories.getName());
    Glide.with(holder.itemView).load("file:///android_asset/" + Categories.getImage()).into(holder.cell_img);
  }

  @Override
  public int getItemCount() {
    return arrayList.size();
  }

  public class CategoriesAdapterVH extends RecyclerView.ViewHolder {
    private View itemView;
    private TextView cell_txt;
    private ImageView cell_img;

    public CategoriesAdapterVH(@NonNull View itemView) {
      super(itemView);
      this.cell_txt = itemView.findViewById(R.id.cell_nameTxt);
      this.cell_img = itemView.findViewById(R.id.cell_img);
      this.itemView = itemView;

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          FragmentTransaction fragmentTransaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();

          fragmentTransaction.replace(R.id.nav_host_fragment, new HomeFragment(getLayoutPosition()));
          fragmentTransaction.addToBackStack(null);
          fragmentTransaction.commit();
        }
      });
    }
  }
}
