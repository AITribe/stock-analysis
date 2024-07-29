package com.example.stockanalysis.api;
import com.example.stockanalysis.model.StockData;
import com.example.stockanalysis.service.DataHolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataHolderService dataHolderService;

    public DataController(DataHolderService dataHolderService) {
        this.dataHolderService = dataHolderService;
    }

    @GetMapping
    public Map<String, StockData> getData() {
        return dataHolderService.getDataMap();
    }
}
