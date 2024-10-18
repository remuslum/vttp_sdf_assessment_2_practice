package com.visa.csv;

public class CleanCSV {
    private boolean inQuotation;

    public CleanCSV() {
        this.inQuotation = false;
    }

    public String cleanLine(String string) {
        char[] stringToChar = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char letter : stringToChar) {
            if (letter != '"' && !this.inQuotation) {
                sb.append(letter);
            }
            if (letter != '"' && this.inQuotation) {
                if (letter == ',') {
                    sb.append("");
                } else {
                    sb.append(letter);
                }
            }
            if (letter == '"') {
                this.inQuotation = !this.inQuotation;
            }
        }
        return sb.toString();
    }

    
}
