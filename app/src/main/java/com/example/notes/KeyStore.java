package com.example.notes;

public interface KeyStore {

    boolean saveNewPin(String pin);

    String hasPin();

    boolean checkPin(String pin);
}
