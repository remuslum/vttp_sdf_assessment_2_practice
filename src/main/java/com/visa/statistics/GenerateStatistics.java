package com.visa.statistics;

public class GenerateStatistics {
    private final Statistics statistics;

    public GenerateStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void generateStats() {
        String category = statistics.getCategory();
        System.out.println(category);
        System.out.printf("Average rating for %s: %.2f \n", category, statistics.getAverageRating());
        String[] highest = statistics.getHighestRatedApp().split(",");
        System.out.printf("Highest Rated App: %s, Highest Rating: %s \n", highest[0], highest[1]);
        String[] lowest = statistics.getLowestRatedApp().split(",");
        System.out.printf("Lowest Rated App: %s, Lowest Rating: %s \n", lowest[0], lowest[1]);
        System.out.printf("Total Number of Applications: %d \n", statistics.getNumberOfApplications());
        System.out.printf("Total Number of Rejected Applications: %d \n", statistics.getNumberOfRejectedRecords());
    }
}
