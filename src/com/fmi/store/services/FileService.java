package com.fmi.store.services;

import com.fmi.store.model.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

public class FileService {

    private static FileService ourInstance = new FileService();

    public static FileService getInstance() {
        return ourInstance;
    }


    private FileService() {
    }

    private void writeTextToFile (String textToWrite, String fileNamePath) {

        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileNamePath,true));

            printWriter.println();
            printWriter.print(textToWrite);

            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeUserToFile(User thisUser, String fileNamePath){

        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(thisUser.getUsername())
                .append(",").append(thisUser.getPassword())
                .append(",").append(thisUser.getAge())
                .append(",").append(thisUser.getEmail())
                .append(",").append(thisUser.getPhoneNumber());

        if(thisUser instanceof Driver) stringBuilder.append(",").append(((Driver) thisUser).getCategory());
        writeTextToFile(stringBuilder.toString(),fileNamePath);

    }

    public void writeReservationToFile(Reservation reservation, String fileNamePath){

        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM 2019 HH:mm");

        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(reservation.getUser().getId())
                .append(",").append(dateFormat.format(reservation.getPickUpInfo().getDate()))
                .append(",").append(reservation.getPickUpInfo().getCountry())
                .append(",").append(reservation.getPickUpInfo().getCity())
                .append(",").append(dateFormat.format(reservation.getDropOffInfo().getDate()))
                .append(",").append(reservation.getDropOffInfo().getCountry())
                .append(",").append(reservation.getDropOffInfo().getCity())
                .append(",").append(reservation.getBike().getId())
                .append(",").append(reservation.getUserPreferences().getSecurityLock())
                .append(",").append(reservation.getUserPreferences().getNumberHelmets())
                .append(",").append(reservation.getCard().getCardNumber())
                .append(",").append(reservation.getCard().getExpirationDate().getYear())
                .append(",").append(reservation.getCard().getExpirationDate().getMonth())
                .append(",").append(reservation.getCard().getCVC());

        writeTextToFile(stringBuilder.toString(),fileNamePath);

    }

    public void readObjectsFromFile(String fileNamePath, String objectType) {
        try {

            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(fileNamePath));

            String line;
            String[] values;

            // skip the first line (the header) in ReservationsData
            if(objectType.equals("Reservation")) lineNumberReader.readLine();

            while(true) {

                line = lineNumberReader.readLine();
                if(line == null) break;
                values = line.split(",");

                if(objectType.equals("User")) createUser(values);
                else if(objectType.equals("Bike")) createBike(values);
                else if(objectType.equals("Reservation")) createReservations(values);

            }

            lineNumberReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createUser(String[] values){
        String category = null;
        if (values.length > 5) category = values[5];
        UserService.createUser(values[0], values[1], Integer.parseInt(values[2]), values[3], values[4], category);
    }

    private void createBike(String[] values){

        TreeSet<String> categories = new TreeSet<>();
        int categoriesCounter = Integer.parseInt(values[7]);
        int lastValueId = 8 + categoriesCounter;
        if(categoriesCounter == 0) categories = null;
            else
                for(int i=8; i< lastValueId; i++)
                    categories.add(values[i]);

        if(values[2].equals("Electric Bike"))
            BikeService.addBike(values[0], values[1], values[2], Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]),
                    Integer.parseInt(values[6]), categories, values[lastValueId], Integer.parseInt(values[lastValueId+1]));
            else BikeService.addBike(values[0], values[1], values[2], Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]),
                    Integer.parseInt(values[6]), categories, null, 0);

    }

    private Location readLocationFromFile(String[] values, int index){

        return new Location(values[index+1],values[index+2], new Date(values[index]));

    }

    private void createReservations(String[] values){

        int helmetCounter = Integer.parseInt(values[9]);
        UserPreferences thisUserPreferences;
        if(helmetCounter == 0)
            thisUserPreferences = new UserPreferences(Boolean.parseBoolean(values[8]),false);
            else
                thisUserPreferences = new UserPreferences(Boolean.parseBoolean(values[8]),true,helmetCounter);

        ReservationService.createReservation(UserService.getOne(Integer.parseInt(values[0])), readLocationFromFile(values,1),
                readLocationFromFile(values,4), BikeService.getOne(Integer.parseInt(values[7])), thisUserPreferences,
                new Payment(values[10],new Date(Integer.parseInt(values[11]),Integer.parseInt(values[12]),1),Integer.parseInt(values[13])));

    }

}
