package com.mukesh.grocery.utility;

public interface OfferUtil {
    static String calculateDiscount(double originalPrice, double offerPrice){
         double offerPercent = ((originalPrice - offerPrice)/originalPrice)*100;
         StringBuilder stringBuilder = new StringBuilder(String.valueOf(Math.round(offerPercent)));
         stringBuilder.append("%");
         return stringBuilder.toString();
    }
}
