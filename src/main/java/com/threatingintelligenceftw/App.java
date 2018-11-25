package com.threatingintelligenceftw;

import com.threatingintelligenceftw.Database.JSONToCVE;
import com.threatingintelligenceftw.Database.NVDCVE;

import com.threatingintelligenceftw.GUI.MainFrame;
import org.json.JSONException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {

    public static final String LOCALDB_PATH = System.getProperty("user.dir") + File.separator + "cve.obj";
    public static final String VULDB_KEY_PATH = System.getProperty("user.dir") + File.separator + "vuldb.key";

    public static void main(String[] args) {
//        String cve = "CVE-2014-6271";
//        VulDBHandler handler = new VulDBHandler("93a9a6f7711d8a96eb8a7885aceb71cc", cve);
//        String response = handler.getResponse();
//        System.out.println(response);
        File localDb = new File(LOCALDB_PATH);
        if (!localDb.exists()) {
            System.out.print("Please provide the path to the .json file: ");
            Scanner in = new Scanner(System.in);
            String path = in.nextLine();
            //String path = "/home/alexander/Downloads/nvdcve-1.0-2018.json";

            try {
                JSONToCVE a = new JSONToCVE(readFile(path));
                saveCVEList(a.getCveList());
            } catch (JSONException e) {
                System.err.println("Couldn't parse json");
            }
        }

        new MainFrame();
    }

    private static String readFile(String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Couldn't read the json file");
        }
        return null;
    }

    private static void saveCVEList(List<NVDCVE> list) {
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
