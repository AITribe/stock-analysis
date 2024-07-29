package com.example.stockanalysis.analyze;

import com.example.stockanalysis.model.StockData;
import com.example.stockanalysis.service.DataHolderService;
import jakarta.annotation.PostConstruct;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoadHistoricalData {

    @Value("${app.data.folder}")
    private String dataFolder;

    private final DataHolderService dataHolderService;

    public LoadHistoricalData(DataHolderService dataHolderService) {
        this.dataHolderService = dataHolderService;
    }

    @PostConstruct
    public void init() throws MalformedURLException, FileNotFoundException {
        readExcelData(); // Run the task immediately at startup
    }

    @Scheduled(cron = "0 0 0 * * ?") // This will run the task daily at midnight
    public void readExcelData() throws MalformedURLException, FileNotFoundException {
        String filePath = dataFolder + File.separator + "data4.csv";
        Resource resource = new UrlResource("file:" + filePath);

    readExcelFile(resource);
    }


    public void readExcelFile(Resource resource) {
        String line;
        String csvSplitBy = "\",\"";

        Map<String, StockData> stockDataMap = new HashMap<>();
        String filePath = dataFolder + File.separator + "data4.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                // Remove leading and trailing double quotes and split by the csvSplitBy pattern
                String[] stockArray = line.substring(1, line.length() - 1).split(csvSplitBy);
                String name = stockArray[0];
                List<String> values = new ArrayList<>();
                for (int i = 1; i < stockArray.length; i++) {
                    values.add(stockArray[i]);
                }
                stockDataMap.put(name, new StockData(name, values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the stock data
        for (Map.Entry<String, StockData> entry : stockDataMap.entrySet()) {
            System.out.println(entry.getValue());
        }

        dataHolderService.setDataMap(stockDataMap);
    }

}
