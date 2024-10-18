package com.visa.statistics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Statistics {
    private final List<Map<String, String>> playStoreData;
    private final String category;

    public Statistics(List<Map<String, String>> playStoreData,String category) {
        this.playStoreData = playStoreData;
        this.category = category;
    }

    public Float getAverageRating() {
        Float ratingSum = 0.00f;
        int count = 0;
        for(Map<String, String> appData:playStoreData) {
            if(category.equals(appData.get("Category")) && !appData.get("Rating").equals("NaN")){
                ratingSum += Float.parseFloat(appData.get("Rating"));
                count++;
            }
        }
        return ratingSum/(float) count;
    }

    public String getHighestRatedApp() {
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

    public String getLowestRatedApp() {
        String lowestRatedApp = "";
        Float minRating = Float.POSITIVE_INFINITY;
        for (Map<String, String> appData:playStoreData){
            if (category.equals(appData.get("Category"))){
                Float appRating = Float.valueOf(appData.get("Rating"));
                if (appRating < minRating) {
                    minRating = appRating;
                    lowestRatedApp = appData.get("App");
                }
            }
        }
        return lowestRatedApp + "," + String.valueOf(minRating);
    }

    public int getNumberOfApplications() {
        List<String> applications = new ArrayList<>();
        for (Map<String, String> appData:playStoreData) {
            if(category.equals(appData.get("Category"))) {
                applications.add(appData.get("App"));
            }
        }
        return new HashSet<>(applications).size();
    }

    public int getNumberOfRejectedRecords() {
        int count = 0;
        for (Map<String, String> appData:playStoreData) {
            if(category.equals(appData.get("Category")) && appData.get("Rating").equals("NaN")) {
                count++;
            }
        }
        return count;
    }

    public String getCategory() {
        return this.category;
    }

    
}
