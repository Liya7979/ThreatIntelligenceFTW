package com.threatingintelligenceftw.GUI;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final String PROMPT = "Please enter your search query here";

    public MainFrame() {
        super("Threat Intelligence FTW");
        add(new SearchPanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center on screen.
        setVisible(true);
    }
}
