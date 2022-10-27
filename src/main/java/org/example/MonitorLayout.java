package org.example;

public class MonitorLayout {
    public static String layout (Integer currentPage, Integer totalPages) {
        return currentPage + 1 + "<b> из </b>" + totalPages;
    }
}
