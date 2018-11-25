package com.threatingintelligenceftw.Database;

import com.threatingintelligenceftw.App;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class DataRetriever {

    private List<LocalCVE> data;

    @SuppressWarnings("unchecked")
    public DataRetriever() {
        try {
            FileInputStream fis = new FileInputStream(App.LOCALDB_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (List<LocalCVE>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("Couldn't reach the local database");
        } catch (ClassNotFoundException e) {
            System.err.println("Incompatible database");
        }
    }

    public List<LocalCVE> getData() {
        return data;
    }

    public JList<String> getVuls(String request) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (LocalCVE entity : data) {
            String lowerCaseRequest = request.toLowerCase();
            if (entity.getId().toLowerCase().contains(lowerCaseRequest) ||
                    (entity.getCVE() != null && entity.getCVE().toLowerCase().contains(lowerCaseRequest)) ||
                    entity.getDescription().toLowerCase().contains(lowerCaseRequest)) {
                model.addElement(entity.getId());
            }
        }
        return new JList<>(model);
    }

    public String getVulInfo(LocalCVE vul) {
        String nn = "\n\n";
        StringBuilder builder = new StringBuilder();
        builder.append("ID:\t\t").append(vul.getId()).append(nn);
        if (vul.getVendor() != null) {
            builder.append(vul.getVendor().toString());
        }
        builder.append("CWE:\t\t").append(vul.getCWE()).append(nn);
        builder.append("References:");
        for (Reference ref : vul.getRefs()) {
            builder.append("\t\t").append(ref.toString()).append('\n');
        }
        builder.append("Description:\t").append(breakDescr(vul.getDescription())).append(nn);
        if (vul.getMetricsV3() != null) {
            builder.append("Impact:\n").append("\tMetricsV3:\n").append(vul.getMetricsV3().toString());
            builder.append("\tMetricsV2:\n").append(vul.getMetricsV2().toString());
        }
        builder.append("Published date:\t").append(vul.getPublishedDate()).append('\n');
        builder.append("Last modified date:\t").append(vul.getLastModifiedDate()).append('\n').append('\n');
        return builder.toString();
    }

    private String breakDescr(String descr) {
        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        String[] broken = descr.split(" ");
        for (int i = 0; i < broken.length; i++) {
            builder.append(broken[i]).append(" ");
            if ((i + 1) % 10 == 0) {
                builder.append("\n\t\t");
            }
        }
        return builder.toString();
    }
}
