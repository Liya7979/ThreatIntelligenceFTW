package com.threatingintelligenceftw.GUI;

import com.threatingintelligenceftw.Controller.SearchLocalAction;
import com.threatingintelligenceftw.Database.DataRetriever;
import com.threatingintelligenceftw.Database.NVDCVE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

class SearchPanel extends JPanel {

    SearchPanel() {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JTextField field = new HintTextField("Please enter your query request ");
        add(field);
        add(new ButtonPanel(field));
        setBackground(new Color(146, 252, 255));
        setVisible(true);
    }
}

class ButtonPanel extends JPanel {

    ButtonPanel(JTextField field) {
        JButton searchLocallyButton = new JButton("Search locally");
        JButton searchOnlineButton = new JButton("Search online");

        searchLocallyButton.addActionListener(new SearchLocalAction(field));

        add(searchLocallyButton);
        add(searchOnlineButton);
        setVisible(true);
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