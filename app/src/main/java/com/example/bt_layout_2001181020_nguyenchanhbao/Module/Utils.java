package com.example.bt_layout_2001181020_nguyenchanhbao.Module;

import android.content.Context;

import com.example.bt_layout_2001181020_nguyenchanhbao.Activity.LoginActivity;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.cert.CertificateRevokedException;
import java.sql.Array;
import java.util.ArrayList;

public class Utils {
  public static Context context;
  public static String filename = "database";

  public static ArrayList<Furniture> furnitureHistory = new ArrayList<>();

  public static void addFurintureHistorry(Furniture furniture) {
    for (int i = 0; i < furnitureHistory.size(); ++i) {
      if (furnitureHistory.get(i).getName().equals(furniture.getName())) return;
    }

    furnitureHistory.add(furniture);
  }
  public static ArrayList<Furniture> getFurnitureHistory(){ return furnitureHistory; }

  public static void WriteToFileInternal(){
    try {
      File file = new File(context.getFilesDir(), filename);
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      ObjectOutputStream objectOutputStream = new
              ObjectOutputStream(fileOutputStream);

//      if (LoadFileInternal() == null) objectOutputStream.writeObject(furnitureHistory);

//      objectOutputStream.writeObject(Furniture.getMockData());

      objectOutputStream.close();
      fileOutputStream.close();

      System.out.println("DONEs");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Furniture> LoadFileInternal(){
    ArrayList<Furniture> arrayList = new ArrayList<>();
    try {
      File file = new File(context.getFilesDir(), filename);

      FileInputStream fileInputStream = new FileInputStream(file);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      arrayList = (ArrayList<Furniture>) objectInputStream.readObject();
      System.out.println("---------HERE_-----------" + arrayList.size()+"");

      return arrayList;

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void writeCustomerToInternal(Customer customer){
    try {
      File file = new File(context.getFilesDir(), "Customer");
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      ObjectOutputStream objectOutputStream = new
              ObjectOutputStream(fileOutputStream);

//      if (LoadFileInternal() == null) objectOutputStream.writeObject(furnitureHistory);

      objectOutputStream.writeObject(customer);

      objectOutputStream.close();
      fileOutputStream.close();

      System.out.println("DONEs");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Customer LoadCustomerInternal(){
    Customer customer = new Customer();
    try {
      File file = new File(context.getFilesDir(), "Customer");

//      if (file.exists()) file.delete();
//      if (!file.exists()) file.createNewFile();

      FileInputStream fileInputStream = new FileInputStream(file);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      customer = (Customer) objectInputStream.readObject();
      System.out.println("---------HERE_-----------" + customer.getName() +"");

      return customer;

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
//
//  public static ArrayList<Furniture> getFurnitureFromCategories(int pos){
//    Categories category = Categories.getCategory(pos);
//
//    return category.getListRoom();
//  }

  public static String truncateString(String str) {
    return str.substring(0, Math.min(str.length(), 120)).concat("...");
  }
}
