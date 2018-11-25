package com.threatingintelligenceftw.Controller;

import com.threatingintelligenceftw.Database.DataRetriever;
import com.threatingintelligenceftw.Database.LocalCVE;

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
    }
}

class BriefDescrFrame extends JFrame {

    private static final Dimension NON_EMPTY = new Dimension(400, 600);
    private static final Dimension EMPTY = new Dimension(450, 100);
    private static final Font EMPTY_FONT = new Font("TimesRoman", Font.BOLD, 20);

    BriefDescrFrame(JList<String> names, List<LocalCVE> data, DataRetriever retriever) {
        names.addMouseListener(new GetDescrOnDoubleClick(data, retriever));
        if (names.getModel().getSize() != 0) {
            JScrollPane scrollPane = new JScrollPane(names);
            getContentPane().add(scrollPane);
            setPreferredSize(NON_EMPTY);
        } else {
            JTextArea emptyListMessage = new JTextArea("Nothing found. Maybe you should\ntry online search?");
            emptyListMessage.setFont(EMPTY_FONT);
            getContentPane().add(emptyListMessage);
            setPreferredSize(EMPTY);
        }
        pack();
        setVisible(true);
    }
}