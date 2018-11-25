package com.threatingintelligenceftw.GUI;

import com.threatingintelligenceftw.Controller.MenuBar;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final String PROMPT = "Please enter your search query here";

    public MainFrame(boolean dbExists) {
        super("Threat Intelligence FTW");
        MenuBar menuBar = new MenuBar();
        SearchPanel searchPanel = new SearchPanel(menuBar, dbExists);
        menuBar.setPanel(searchPanel);
        add(searchPanel);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center on screen.
        setVisible(true);
    }
}
