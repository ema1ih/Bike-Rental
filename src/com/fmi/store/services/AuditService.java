package com.fmi.store.services;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditService {
    private static AuditService ourInstance = new AuditService();

    public static AuditService getInstance() {
        return ourInstance;
    }

    private String historyPath = "C:\\Users\\Ema\\Desktop\\java\\Bike Rental\\src\\HistoryData.csv";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy HH:mm ");

    private AuditService() {

    }

    public void addEventToHistory (String eventName, Date timestamp) {

        try {

            PrintWriter printWriter = new PrintWriter(new FileOutputStream(historyPath,true));

            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append(eventName)
                    .append(",").append(dateFormat.format(timestamp));

            printWriter.println();
            printWriter.print(stringBuilder.toString());

            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
