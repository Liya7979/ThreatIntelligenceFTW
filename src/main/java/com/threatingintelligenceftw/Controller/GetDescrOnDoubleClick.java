package com.threatingintelligenceftw.Controller;

import com.threatingintelligenceftw.Database.DataRetriever;
import com.threatingintelligenceftw.Database.LocalCVE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GetDescrOnDoubleClick extends MouseAdapter {

    List<LocalCVE> data;
    DataRetriever retriever;

    GetDescrOnDoubleClick(List<LocalCVE> data, DataRetriever retriever) {
        this.data = data;
        this.retriever = retriever;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() >= 2) {
            @SuppressWarnings("unchecked") JList<String> list = (JList) mouseEvent.getSource();
            int index = list.locationToIndex(mouseEvent.getPoint());
            if (index >= 0) {
                String cve = list.getModel().getElementAt(index);
                new DescrFrame(description(cve));
            }
        }
    }

    private String description(String cve) {
        for (LocalCVE entity : data) {
            if (entity.getId().equals(cve)) {
                return retriever.getVulInfo(entity);
            }
        }
        return "";
    }
}

class DescrFrame extends JFrame {

    private static final Font FONT = new Font("TimesRoman", Font.BOLD, 15);

    DescrFrame(String description) {
        JTextArea textArea = new JTextArea(description);
        textArea.setFont(FONT);
        getContentPane().add(new JScrollPane(textArea));
        setPreferredSize(new Dimension(800, 800));
        pack();
        setVisible(true);
    }
}
