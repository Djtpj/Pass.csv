package me.djtpj.passwordmanager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PasswordManager {
    private ArrayList<Password> passwords;
    private File csvFile;

    public PasswordManager(File csvFile) {
        this.csvFile = csvFile;
        if (Files.notExists(Path.of("/passwords.csv"))) {

        }
        passwords = new ArrayList<>();
    }

    public void addPassword(Password password) {
        updatePasswords();
        passwords.add(password);
        savePasswordsToCSV();
    }

    public ArrayList<Password> getPasswords() {
        updatePasswords();
        return passwords;
    }

    void savePasswordsToCSV() {
        try (PrintWriter writer = new PrintWriter(csvFile)) {

            updatePasswords();
            for (Password p : passwords) {
                System.out.println(p.getAccountName() + " " + p.getUsername() + " " + p.getPassword());
            }

            StringBuilder sb = new StringBuilder();

            sb.append("Account Name");
            sb.append(",");
            sb.append("Username");
            sb.append(",");
            sb.append("Password");
            sb.append("\n");

            for (Password p : passwords) {
                sb.append(p.getAccountName());
                sb.append(",");
                sb.append(p.getUsername());
                sb.append(",");
                sb.append(p.getPassword());
                sb.append("\n");
            }


            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    void updatePasswords() {
        CSVReader csvReader = new CSVReader(csvFile, ",");

        for (ArrayList<String> l : csvReader.readCSV()) {
            passwords.add(new Password(
                    l.get(0),
                    l.get(1),
                    l.get(2)
            ));
        }
    }


}
