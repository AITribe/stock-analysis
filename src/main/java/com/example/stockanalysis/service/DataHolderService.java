package com.example.stockanalysis.service;
import com.example.stockanalysis.model.StockData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataHolderService {
    private final Map<String, StockData> dataMap = new HashMap<>();

    public Map<String, StockData> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, StockData> dataMap) {
        this.dataMap.clear();
        this.dataMap.putAll(dataMap);
    }
}
