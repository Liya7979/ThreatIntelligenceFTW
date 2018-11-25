package com.threatingintelligenceftw.GUI;

import com.threatingintelligenceftw.Controller.MenuBar;
import com.threatingintelligenceftw.Controller.SearchLocalAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchPanel extends JPanel {

    private boolean dbExists;
    private MenuBar menuBar;
    private ButtonPanel buttonPanel;
    JTextField field;

    public SearchPanel(MenuBar menuBar, boolean dbExists) {
        this.dbExists = dbExists;
        this.menuBar = menuBar;
        field = new HintTextField("Please enter your query request ");
        add(field);
        ButtonPanel panel = new ButtonPanel(this, field, dbExists);
        this.buttonPanel = panel;
        add(panel);
        setBackground(new Color(146, 252, 255));
        setVisible(true);
    }

    public void setSearchLocallyButton(boolean enabled) {
        buttonPanel.getSearchLocallyButton().setEnabled(enabled);
        buttonPanel.setSearchAction(new SearchLocalAction(field));
    }
}

class ButtonPanel extends JPanel {

    private SearchPanel panel;
    private JButton searchLocallyButton;
    private SearchLocalAction searchAction;

    ButtonPanel(SearchPanel panel, JTextField field, boolean dbExists) {
        this.panel = panel;
        searchLocallyButton = new JButton("Search locally");
        JButton searchOnlineButton = new JButton("Search online\n(NOT IMPLEMENTED)");
        searchLocallyButton.setEnabled(dbExists);
        searchOnlineButton.setEnabled(false);

        searchAction = new SearchLocalAction(field);
        searchLocallyButton.addActionListener(searchAction);

        add(searchLocallyButton);
        add(searchOnlineButton);
        setVisible(true);
    }

    public void setSearchAction(SearchLocalAction searchAction) {
        searchLocallyButton.removeActionListener(this.searchAction);
        this.searchAction = searchAction;
        searchLocallyButton.addActionListener(searchAction);
    }

    public JButton getSearchLocallyButton() {
        return searchLocallyButton;
    }
}

class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}