package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchFunction extends AppCompatActivity {


    private ArrayAdapter<String> adapter;

        private List<Product> productList;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_function);

            // Initialize views
            EditText searchInput = findViewById(R.id.search);
            ListView searchResultsListView = findViewById(R.id.searchResultsListView);

            // Initialize product list
            productList = new ArrayList<>();
            // Add sample products
            productList.add(new Product(1, "Bread", "Bakery", "Carton")); // Changed to Carton
            productList.add(new Product(2, "Eggs", "Dairy", "Carton"));
            productList.add(new Product(3, "Milk", "Dairy", "Carton"));
            productList.add(new Product(4, "Apples", "Fruits", "Carton")); // Changed to Carton
            productList.add(new Product(5, "Chicken Breasts", "Meat", "Carton")); // Changed to Carton
            productList.add(new Product(6, "Rice", "Grains", "Carton")); // Changed to Carton
            productList.add(new Product(7, "Pasta", "Grains", "Carton")); // Changed to Carton
            productList.add(new Product(8, "Spinach", "Vegetables", "Carton")); // Changed to Carton
            productList.add(new Product(9, "Tomatoes", "Vegetables", "Carton")); // Changed to Carton
            productList.add(new Product(10, "Cheese", "Dairy", "Carton")); // Changed to Carton
            productList.add(new Product(11, "Yogurt", "Dairy", "Plastic"));
            productList.add(new Product(12, "Cereal", "Breakfast", "Carton")); // Changed to Carton
            productList.add(new Product(13, "Butter", "Dairy", "Aluminium Foil"));
            productList.add(new Product(14, "Orange Juice", "Beverages", "Bottle"));
            productList.add(new Product(15, "Toilet Paper", "Household", "Carton")); // Changed to Carton
            productList.add(new Product(16, "Shampoo", "Personal Care", "Bottle"));
            productList.add(new Product(17, "Soap", "Personal Care", "Bottle"));
            productList.add(new Product(18, "Toothpaste", "Personal Care", "Tube"));
            productList.add(new Product(19, "Dish Soap", "Household", "Bottle"));
            productList.add(new Product(20, "Laundry Detergent", "Household", "Bottle"));
            // Add more products as needed

            // Initialize ArrayAdapter with product names
            List<String> productNames = getProductNames(productList);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productNames);
            searchResultsListView.setAdapter(adapter);

            // Set up search functionality
            searchInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterProducts(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        private List<String> getProductNames(List<Product> productList) {
            List<String> productNames = new ArrayList<>();
            for (Product product : productList) {
                productNames.add(product.getName());
            }
            return productNames;
        }

        private void filterProducts(String query) {
            List<Product> filteredProducts = new ArrayList<>();
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(query.toLowerCase()) ||
                        String.valueOf(product.getId()).toLowerCase().contains(query.toLowerCase()) ||
                        product.getCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredProducts.add(product);
                }
            }
            List<String> filteredProductNames = getProductNames(filteredProducts);
            adapter.clear();
            adapter.addAll(filteredProductNames);
            adapter.notifyDataSetChanged();
        }
    }