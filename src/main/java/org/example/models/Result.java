package org.example.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Result {
    private Long count;
    private Long start;
    private Long rows;
    private ArrayList<ItemElement> items;

}