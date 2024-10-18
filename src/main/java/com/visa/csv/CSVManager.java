package com.visa.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVManager {
    private final String dirPath;
    private final String fileName;
    private final CleanCSV cleaner;

    public CSVManager(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
        this.cleaner = new CleanCSV();
    }

    public List<Map<String, String>> getCSVData() throws IOException {
        FileReader fileReader = new FileReader(new File(dirPath + File.separator + fileName));
        BufferedReader br = new BufferedReader(fileReader);
        String[] headers = br.readLine().split(",");
        String line = "";
        List<Map<String, String>> playStoreData = new ArrayList<>();
        int numberOfLines = 0;

        while ((line = br.readLine()) != null) {
            numberOfLines++;
            String cleanedLine = cleaner.cleanLine(line);
            Map<String, String> temp = new HashMap<>();
            String[] content = cleanedLine.split(",");

            if (content[0].equals("Life Made WI-Fi Touchscreen Photo Frame")){
                String[] tempList = new String[13];
                tempList[0] = content[0];
                tempList[1] = "UNKNOWN";
                for (int i = 2; i < tempList.length; i++) {
                    tempList[i] = content[i-1];
                }
                content = tempList;
            }
    
            for (int i = 0; i < headers.length; i++) {
                temp.put(headers[i], content[i]);
            }
            playStoreData.add(temp);
        }
        br.close();

        String[] relevantColumns = {"App", "Category", "Rating"};
        System.out.printf("Number of Lines Read: %d \n",numberOfLines);
        return extractRelevantColumns(relevantColumns, playStoreData);
    }

    public List<Map<String, String>> extractRelevantColumns(String[] columns, List<Map<String, String>> playStoreData) {
        List<Map<String, String>> relevantPlayStoreData = new ArrayList<>();
        for(Map<String, String> appData:playStoreData){
            Map<String, String> temp = new HashMap<>();
            for (String column:columns){
                temp.put(column, appData.get(column));
            }
            relevantPlayStoreData.add(temp);
        }
        return relevantPlayStoreData;
    }

    public List<String> categoryList(List<Map<String, String>> playStoreData) {
        List<String> categoryList = new ArrayList<>();
        for(Map<String, String> appData:playStoreData) {
            String category = appData.get("Category");
            if(!categoryList.contains(category)){
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
