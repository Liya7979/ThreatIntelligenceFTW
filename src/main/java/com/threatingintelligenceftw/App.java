package com.threatingintelligenceftw;

import com.threatingintelligenceftw.Database.JSONToCVE;
import com.threatingintelligenceftw.Database.NVDCVE;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, JSONException {
//        String cve = "CVE-2014-6271";
//        VulDBHandler handler = new VulDBHandler("93a9a6f7711d8a96eb8a7885aceb71cc", cve);
//        String response = handler.getResponse();
//        System.out.println(response);
        System.out.print("Please provide the path to the .json file: ");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        //String path = "/home/alexander/Downloads/nvdcve-1.0-2018.json";
        JSONToCVE a = new JSONToCVE(readFile(path));
        //NVDData.saveData(a.getCveList());
        saveCVEList(a.getCveList());
    }

    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);

    }

    private static void saveCVEList(List<NVDCVE> list) {
        try {
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "cve.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
