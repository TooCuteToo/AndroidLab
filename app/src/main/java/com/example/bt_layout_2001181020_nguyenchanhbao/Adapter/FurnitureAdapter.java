package com.example.bt_layout_2001181020_nguyenchanhbao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_layout_2001181020_nguyenchanhbao.Activity.DetailActivity;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Furniture;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.util.List;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.FurnitureAdapterVH> {
  private Context context;
  private List<Furniture> arrayList;
  public int postion;

  public FurnitureAdapter(Context context, List<Furniture> arrayList) {
    this.context = context;
    this.arrayList = arrayList;
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public List<Furniture> getArrayList() {
    return arrayList;
  }

  public void setArrayList(List<Furniture> arrayList) {
    this.arrayList = arrayList;
  }

  @NonNull
  @Override
  public FurnitureAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);

    return new FurnitureAdapterVH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FurnitureAdapterVH holder, int position) {
    Furniture furniture = arrayList.get(position);

    holder.product_title.setText(furniture.getName());
    holder.product_desc.setText(Utils.truncateString(furniture.getDescription()));

    Glide.with(holder.itemView).load("file:///android_asset/" + furniture.getImage()).into(holder.product_img);

  }

  @Override
  public int getItemCount() {
    return arrayList.size();
  }

  public class FurnitureAdapterVH extends RecyclerView.ViewHolder {
    private View itemView;
    private TextView product_title;
    private ImageView product_img;
    private TextView product_desc;

    public FurnitureAdapterVH(@NonNull View itemView) {
      super(itemView);
      this.product_title = itemView.findViewById(R.id.layout_titleText);
      this.product_img = itemView.findViewById(R.id.layout_picture);
      this.product_desc = itemView.findViewById(R.id.layout_descriptionText);
      this.itemView = itemView;

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Utils.addFurintureHistorry(arrayList.get(getLayoutPosition()));
          Toast.makeText(itemView.getContext(), getLayoutPosition() +"", Toast.LENGTH_SHORT).show();

          Utils.context = itemView.getContext();
//        Utils.WriteToFileInternal();

          Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
          intent.putExtra("furniture", arrayList.get(getLayoutPosition()));
          itemView.getContext().startActivity(intent);
        }
      });

      itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          postion = getLayoutPosition();
          return false;
        }
      });
    }
  }
}
