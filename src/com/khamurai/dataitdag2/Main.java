package com.khamurai.dataitdag2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        while(true) {
            System.out.println("1. Generate/select keypair");
            System.out.println("2. Encrypt/decrypt string");
            System.out.println("3. Encrypt/decrypt text file");
            System.out.println("9. Exit");
            switch(userInput.nextLine()) {
                case "1":
                    generateKeys(userInput);
                    break;
                case "2":
                    encryptDecryptString(userInput);
                    break;
                case "3":
                    encryptDecryptTextFile(userInput);
                    break;
                case "9":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");

            }
        }
    }

    static void generateKeys(Scanner userInput) {
        System.out.println("Enter key:");
        if(userInput.hasNext()) {
            String input = userInput.nextLine();
            KeyGenerator.generateKeys(input);
            KeyChain.setActiveKeyPair(input);
        }
    }

    static void encryptDecryptTextFile(Scanner userInput) {
        if(KeyChain.getPrivateKey() == null || KeyChain.getPublicKey() == null) {
            System.out.println("Generate or select a keypair before encrypting!");
        } else {
            System.out.println("Enter a file to be encrypted/decrypted");
            if(userInput.hasNext()) {
                String input = userInput.nextLine();
                Encryptor.encryptTextFile(input, KeyChain.getPublicKey());
                Encryptor.decryptTextFile(input, KeyChain.getPrivateKey());
            }
        }
    }

    static void encryptDecryptString(Scanner userInput) {
        if(KeyChain.getPublicKey() == null || KeyChain.getPrivateKey() == null) {
            System.out.println("Generate or select a keypair before encrypting!");
        } else {
            System.out.println("Enter message to encrypt/decrypt:");
            if(userInput.hasNext()) {
                String input = userInput.nextLine();
                String encrypted = Encryptor.encryptString(input, KeyChain.getPublicKey());
                System.out.println("Encrypted: " + encrypted);
                String clear = Encryptor.decryptString(encrypted, KeyChain.getPrivateKey());
                System.out.println("Decrypted: " + clear);
            }
        }
    }
}
