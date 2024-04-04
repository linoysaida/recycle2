package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
            EditText searchInput = findViewById(R.id.searchInput);
            ListView searchResultsListView = findViewById(R.id.searchResultsListView);


            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// מאזין ללחיצות על פריטים בתפריט התחתון
            bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
            // Populate the spinner with material options


            searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showProductDetailsDialog(productList.get(position));
                }
            });



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


    private boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(SearchFunction.this, HomePage.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }

        if (item.getItemId() == R.id.action_logout) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(SearchFunction.this, LogIn.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }
        return false;
    }

    private void showProductDetailsDialog(Product product) {
        // Create a dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_product_details);

        // Initialize dialog views with product details
        TextView productNameTextView = dialog.findViewById(R.id.productNameTextView);
        productNameTextView.setText(product.getName());

        // Initialize other views...

        // Set up close button
        Button closeButton = dialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> dialog.dismiss());

        // Set up find bin button
        Button findBinButton = dialog.findViewById(R.id.findBinButton);
        findBinButton.setOnClickListener(v -> {
            // Implement action to find the disposal bin
            dialog.dismiss();
        });

        // Get the window of the dialog
        Window window = dialog.getWindow();
        if (window != null) {
            // Set the width of the dialog to 70% of the screen width
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int dialogWidth = (int)(displayMetrics.widthPixels * 0.7);
            int dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT; // Adjust height as necessary
            window.setLayout(dialogWidth, dialogHeight);

            // Center the dialog in the screen
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.CENTER;
            window.setAttributes(layoutParams);
        }

        dialog.show();

        findBinButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchFunction.this, MapFunction.class);
            intent.putExtra("ProductCategory", product.getCategory());
            startActivity(intent);
            dialog.dismiss();
        });
    }

    }