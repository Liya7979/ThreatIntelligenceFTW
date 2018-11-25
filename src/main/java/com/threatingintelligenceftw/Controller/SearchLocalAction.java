package com.threatingintelligenceftw.Controller;

import com.threatingintelligenceftw.Database.DataRetriever;
import com.threatingintelligenceftw.Database.NVDCVE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SearchLocalAction extends AbstractAction {

    private JTextField field;
    private DataRetriever retriever;

    public SearchLocalAction(JTextField field) {
        this.field = field;
        retriever = new DataRetriever();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String request = field.getText();
        if (request.equals("") || request.equals(" ")) {
            return;
        }
        new BriefDescrFrame(retriever.getVuls(request), retriever.getData(), retriever);
        //JOptionPane.showMessageDialog(null, retriever.getVuls(request));
    }
}

class BriefDescrFrame extends JFrame {

    BriefDescrFrame(JList<String> names, List<NVDCVE> data, DataRetriever retriever) {
        names.addMouseListener(new GetDescrOnDoubleClick(data, retriever));
        JScrollPane scrollPane = new JScrollPane(names);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(400, 800));
        pack();
        setVisible(true);
    }
}