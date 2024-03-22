package com.example.myapplication;


public class Product {
    // שדות כיתה להגדרת מאפייני המוצר
    private int id;
    protected String name;
    protected String category;
    protected String packaging;

    // בנאי שמאתחל את השדות עם הפרמטרים שהועברו
    public Product(int id, String name, String category, String packaging) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.packaging = packaging;
    }

    // Getters ו-Setters לכל שדה אם נדרש (לדוגמה):
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getPackaging() {
        return this.packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }


}
