package com.mukesh.grocery.enums;

public enum Category {
    BASMATI_RICE("Basmati Rice"),
    OLIVE_OIL("Olive Oil");

    private String category;
    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
