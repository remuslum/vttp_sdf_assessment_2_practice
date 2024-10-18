package com.visa;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.visa.csv.CSVManager;
import com.visa.statistics.GenerateStatistics;
import com.visa.statistics.Statistics;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args) throws IOException{
        CSVManager csvManager = new CSVManager("/Users/remuslum/Downloads/vttp_sdf/vttp_sdf_assessment_2_practice/data", "googleplaystore.csv");
        
        List<Map<String,String>> playStoreData = csvManager.getCSVData();
        List<String> categories = csvManager.categoryList(playStoreData);
        for(String category:categories) {
            GenerateStatistics generateStatistics = new GenerateStatistics(new Statistics(playStoreData, category));
            generateStatistics.generateStats();
        }
    }
}
