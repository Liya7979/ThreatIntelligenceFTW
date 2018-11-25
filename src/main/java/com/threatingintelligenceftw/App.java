package com.threatingintelligenceftw;

import com.threatingintelligenceftw.Database.JsonToCVE;
import com.threatingintelligenceftw.Database.LocalCVE;

import com.threatingintelligenceftw.GUI.MainFrame;
import com.threatingintelligenceftw.Network.VulDBHandler;
import org.json.JSONException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

// TODO: ONLINE SEARCH. PRIMARY SOURCE: https://vuldb.com/
public class App {

    public static final String LOCALDB_PATH = System.getProperty("user.dir") + File.separator + "cve.obj";

    public static void main(String[] args) {
        File localDb = new File(LOCALDB_PATH);
        boolean dbExists = localDb.exists();
//            try {
//                JsonToCVE a = new JsonToCVE(readFile(path));
//                saveCVEList(a.getCveList());
//            } catch (JSONException e) {
//                System.err.println("Couldn't parse json");
//            }


        new MainFrame(dbExists);
    }


}
