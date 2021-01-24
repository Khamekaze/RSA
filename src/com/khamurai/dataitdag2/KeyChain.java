package com.khamurai.dataitdag2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class KeyChain {

    private static KeyPair publicKey;
    private static KeyPair privateKey;

    public static KeyPair getPublicKey() {
        return publicKey;
    }

    public static KeyPair getPrivateKey() {
        return privateKey;
    }

    public static void flushKeys() {
        publicKey = null;
        privateKey = null;
    }

    public static void setActiveKeyPair(String fileName) {
        try {
            //Public
            FileInputStream fileIn = new FileInputStream(fileName + "_pub.key");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            publicKey = (KeyPair) objIn.readObject();
            objIn.close();
            fileIn.close();

            //Private
            FileInputStream fileInPriv = new FileInputStream(fileName + "_priv.key");
            ObjectInputStream objInPriv = new ObjectInputStream(fileInPriv);
            privateKey = (KeyPair) objInPriv.readObject();
            objInPriv.close();
            fileInPriv.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
