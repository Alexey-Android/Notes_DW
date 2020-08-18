package com.example.notes;

public interface KeyStore {

    boolean writeToFile(String str, String fileName);
    String readFromFile(String fileName);

}
