package com.mukesh.grocery.enums;

public enum Units {
    KG("Kg"),
    GRAM("gm"),
    LITRES("litres");

    private String unit;
    Units(String unit) {
        this.unit =  unit;
    }

    public String getUnit() {
        return unit;
    }
}
