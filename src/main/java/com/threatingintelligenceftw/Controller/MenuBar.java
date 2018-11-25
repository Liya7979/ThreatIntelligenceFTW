package com.threatingintelligenceftw.Controller;

import com.threatingintelligenceftw.Database.JsonToCVE;
import com.threatingintelligenceftw.Database.LocalCVE;
import com.threatingintelligenceftw.GUI.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.threatingintelligenceftw.GUI.SearchPanel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.threatingintelligenceftw.App.LOCALDB_PATH;

public class MenuBar extends JMenuBar {

    private SearchPanel panel;

    public MenuBar() {
        add(new FileMenu(this));
    }

    public void setPanel(SearchPanel panel) {
        this.panel = panel;
    }

    void fileLoaded() {
        panel.setSearchLocallyButton(true);
    }
}

class FileMenu extends JMenu {

    FileMenu(MenuBar menuBar) {
        super("File");
        menuBar.add(this);
        JMenuItem loadJson = new JMenuItem("Load JSON (can be retrieved from https://nvd.nist.gov/vuln/data-feeds#JSON_FEED)");
        loadJson.addActionListener(actionEvent -> {
            JFileChooser loader = new JFileChooser();
            loader.setCurrentDirectory(new File(System.getProperty("user.home")));
            loader.addChoosableFileFilter(new FileNameExtensionFilter("JSON", "json"));
            int success = loader.showOpenDialog(null);

            if (success == JFileChooser.APPROVE_OPTION) {
                File file = new File(loader.getSelectedFile().getPath());
                JsonToCVE jsonParser = new JsonToCVE(JsonLoader.readFile(file.getPath()));
                JsonLoader.saveCVEList(jsonParser.getCveList());
                menuBar.fileLoaded();
            }
        });
        add(loadJson);
    }
}

class JsonLoader {
    static String readFile(String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Couldn't read the json file");
        }
        return null;
    }

    static void saveCVEList(List<LocalCVE> list) {
        try {
            FileOutputStream fos = new FileOutputStream(LOCALDB_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}