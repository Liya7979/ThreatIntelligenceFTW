package com.threatingintelligenceftw.Network;

import java.io.File;

public class VulDBKeyManager {

    private String key;

    public VulDBKeyManager(String key) {
        this.key = key;
    }

    public void changeKey(String newKey) {
        File keyFile = new File(key);
        if (keyFile.exists()) {
            keyFile.delete();
        }

    }
}
