package me.djtpj.passwordmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    List<ArrayList<String>> records = new ArrayList<>();
    File file;
    Scanner scanner;
    String delimiter;

    public CSVReader(File csvFile, String delimiter){
        file = csvFile;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.delimiter = delimiter;
    }

    private ArrayList<String> getRecordFromLine(String nextLine) {
        ArrayList<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(nextLine)) {
            rowScanner.useDelimiter(delimiter);
            while(rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        return values;
    }

    public List<ArrayList<String>> readCSV() {
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();

            records.add(getRecordFromLine(nextLine));
        }
        records.remove(0);
        return records;
    }
}
