package me.djtpj.passwordmanager;

import me.djtpj.passwordmanager.ui.MainForm;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static PasswordManager passwordManager;

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();

        passwordManager = new PasswordManager(new File("Passwords.csv"));
    }

    public static ArrayList<Password> getPasswords() {
        return passwordManager.getPasswords();
    }
}
