package com.visa.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private final List<Map<String, String>> playStoreData;
    private final List<String> categoryList;

    public Statistics(List<Map<String, String>> playStoreData, List<String> categoryList) {
        this.playStoreData = playStoreData;
        this.categoryList = categoryList;
    }

    public Map<String, Float> getAverageRatings() {
        Map<String, Float> ratings = new HashMap<>();
        for (String category:categoryList) {
            Float ratingSum = 0.00f;
            int count = 0;
            for(Map<String, String> appData:playStoreData) {
                if(category.equals(appData.get("Category")) && !appData.get("Rating").equals("NaN")){
                    ratingSum += Float.parseFloat(appData.get("Rating"));
                    count++;
                }
            }
            ratings.put(category, ratingSum/(float)count);
        }
        return ratings;
    }

    public String getHighestRatedApp(String category) {
        String highestRatedApp = "";
        Float maxRating = 0.00f;
        for (Map<String, String> appData:playStoreData){
            if (category.equals(appData.get("Category"))){
                Float appRating = Float.valueOf(appData.get("Rating"));
                if (appRating > maxRating) {
                    maxRating = appRating;
                    highestRatedApp = appData.get("App");
                }
            }
        }
        return highestRatedApp + "," + String.valueOf(maxRating);
    }

    
}
