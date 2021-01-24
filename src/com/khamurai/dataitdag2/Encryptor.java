package com.khamurai.dataitdag2;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encryptor {

    public static String encryptString(String message, KeyPair keyPair) {
        String msg = new String(message.getBytes(), StandardCharsets.UTF_8);
        return (new BigInteger(msg.getBytes())).modPow(keyPair.getKey(), keyPair.getN()).toString();
    }

    public static String decryptString(String message, KeyPair keyPair) {
        String msg = new String(message.getBytes(), StandardCharsets.UTF_8);
        return new String((new BigInteger(msg)).modPow(keyPair.getKey(), keyPair.getN()).toByteArray());
    }

    public static void decryptTextFile(String fileName, KeyPair keyPair) {
        File f = new File(fileName + ".txt");
        if(!f.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        String fileText = "";
        try {
            fileText = new String(Files.readAllBytes(Path.of(fileName + ".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String decryptedText = decryptString(fileText, keyPair);

        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(decryptedText);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void encryptTextFile(String fileName, KeyPair keyPair) {
        File f = new File(fileName + ".txt");
        if(!f.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        String fileText = "";
        try {
            fileText = new String(Files.readAllBytes(Path.of(fileName + ".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encryptedString = encryptString(fileText, keyPair);
        System.out.println(encryptedString);

        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(encryptedString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
