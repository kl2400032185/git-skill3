package com.hqllab.util;

import java.util.Scanner;

public class UserInputHandler {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Get a string input from user
     */
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Get a double input from user
     */
    public static Double getDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            return getDouble(prompt);
        }
    }
    
    /**
     * Get an integer input from user
     */
    public static Integer getInteger(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            return getInteger(prompt);
        }
    }
    
    /**
     * Get a yes/no input from user
     */
    public static boolean getYesNo(String prompt) {
        String response = getString(prompt + " (y/n): ").toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
    
    /**
     * Display a menu and get choice
     */
    public static int getMenuChoice(String... options) {
        System.out.println("\n========== MENU ==========");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("========================\n");
        
        int choice = getInteger("Enter your choice (1-" + options.length + "): ");
        if (choice < 1 || choice > options.length) {
            System.out.println("Invalid choice! Please try again.");
            return getMenuChoice(options);
        }
        return choice;
    }
    
    /**
     * Close the scanner
     */
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
