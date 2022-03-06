package me.djtpj.passwordmanager.ui;

import me.djtpj.passwordmanager.Main;
import me.djtpj.passwordmanager.Password;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainForm extends JFrame{
    private JPanel panel;
    private JTextField searchBar;
    private JButton searchButton;
    private JPasswordField passwordField;
    private JTabbedPane tabs;
    private JTextField accountField;
    private JButton submitButton;
    private JTextArea notes;
    private JTextArea expandedView;
    private JTextField usernameField;
    private JList results;
    private Image icon;

    public MainForm() {

        icon = Toolkit.getDefaultToolkit().getImage("C:\\Dev\\Java\\Software\\Password Manager\\res\\Password Manager Icon.png");

        setContentPane(panel);
        setTitle("Password Manager Pro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(960, 540));
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Dev\\Java\\Software\\Password Manager\\res\\Password Manager Icon.png"));
        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!accountField.getText().isEmpty()) && (!usernameField.getText().isEmpty()) && (!String.valueOf(passwordField.getPassword()).isEmpty())) {
                    Main.passwordManager.addPassword(new Password(
                            accountField.getText(),
                            usernameField.getText(),
                            String.valueOf(passwordField.getPassword())));

                    accountField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");

                    notes.setText(Main.passwordManager.getPasswords().toString());
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getSearchBar() {
        return searchBar;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JPasswordField getPasswords() {
        return passwordField;
    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    public JTextField getAccountField() {
        return accountField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextArea getNotes() {
        return notes;
    }

    public JTextArea getExpandedView() {
        return expandedView;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JList getResults() {
        return results;
    }

    private ArrayList<Password> search(String keyWord) {
        ArrayList<Password> results = new ArrayList<>();

        for (Password p : Main.getPasswords()) {
            if (p.getAccountName().contains(keyWord) || p.getUsername().contains(keyWord) || p.getPassword().contains(keyWord)) {
                results.add(p);
            }
        }

        return results;
    }

    private void displayResults(ArrayList<Password> results) {
        for (Password p : results) {
            this.results.add(p.getAccountName(), new JLabel(p.getAccountName()));
        }
    }

}
