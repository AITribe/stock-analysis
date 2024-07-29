package com.example.stockanalysis.model;

import lombok.Data;

import java.util.List;

@Data
public class StockData {
    String name;
    List<String> values;

    public StockData(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }


}
