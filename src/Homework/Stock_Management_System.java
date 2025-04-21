package Homework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Stock_Management_System {
    public static void main(String[] args) {
        int choice=0, rows=0;
        String[][] stock = null;
        boolean stockInt = false;
        LocalDateTime[][] insertDate = null;

        Scanner input = new Scanner(System.in);

        do{
            System.out.println("\n"+ "=".repeat(60));
            System.out.println("*".repeat(10) +"<> Welcome to Stock Management System <>"+ "*".repeat(10));
            System.out.println("=".repeat(60)+ "\n");

            System.out.println("""
                    [1] => Set Up Stock.
                    [2] => View Stock.
                    [3] => Insert Product in Stock.
                    [4] => Update Product in Stock.
                    [5] => Delete Product from Stock.
                    [6] => View Insertion History.
                    [7] => Exits.
                    """);
            System.out.print("Please Enter Your Choice: ");

            try{
                choice = input.nextInt();
            }catch(InputMismatchException e){
                System.out.println("[!] Please enter a valid choice");
                input.nextLine();
                continue;
            }

            switch (choice){
                case 1 -> {
                    System.out.print("Please Enter The number of Stock: ");
                    try{
                        rows = new Scanner(System.in).nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("[!] Please enter a valid number");
                        input.nextLine();
                        break;
                    }
                    stock = new String[rows][];
                    insertDate = new LocalDateTime[rows][];

                    for(int i = 0; i < rows; i++){
                        System.out.print("Please Enter The number of Catalogue: ["+ (i+1)+"] ");
                        try{
                            int cols = new Scanner(System.in).nextInt();
                            stock[i] = new String[cols];
                            insertDate[i] = new LocalDateTime[cols];

                        }catch (InputMismatchException e){
                            System.out.println("[!] Invalid number. Skipping stock insertion");
                            input.nextLine();
                            stock[i] = new String[0];
                            insertDate[i] = new LocalDateTime[0];
                        }

                    }

                    for(int i = 0; i < rows; i++){
                        for(int j=0; j<stock[i].length; j++){
                            stock[i][j] = "Empty";
                            insertDate[i][j] = null;
                        }
                    }

                    System.out.println("========= Set Up Stock Successes =========");
                    for (int i = 0; i < rows; i++) {
                        System.out.println("Stock ["+ (i+1)+"]");
                        for (int j = 0; j < stock[i].length; j++) {
                            System.out.println("["+ (j+1)+ "]"+ stock[i][j]);
                        }
                        System.out.println();
                    }
                    stockInt = true;
                }
                case 2 -> {
                    if (!stockInt) {
                        System.out.println("Stock not Set Up yet! Choose Option [1]");
                    } else {
                        for (int i = 0; i < stock.length; i++) {
                            boolean stockFull = true;
                            System.out.print("Stock[" + (i + 1) + "] ==> ");
                            for (int j = 0; j < stock[i].length; j++) {
                                if (stock[i][j].equalsIgnoreCase("Empty")) {
                                    System.out.print("[" + (j + 1) + " - " + stock[i][j] + "] ");
                                    stockFull = false;
                                }
                                System.out.print("[" + stock[i][j] + "] ");
                            }
                            if (stockFull) {
                                System.out.print("=> Stock Full");
                            }
                            System.out.println();
                        }
                    }
                    input.nextLine();
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }
                case 3 -> {
                    System.out.println("-------------- Insert Product To Stock --------------");
                    if (!stockInt) {
                        System.out.println("[*[ Stock not Set Up yet! Choose Option [1]");
                    } else {
                        boolean hasEmpty = false;
                        System.out.print("Stock Number Available : ");
                        for (int i = 0; i < stock.length; i++) {
                            for (int j = 0; j < stock[i].length; j++) {
                                if (stock[i][j].equalsIgnoreCase("Empty")) {
                                    System.out.print(" [" + (i + 1) + "] | ");
                                    hasEmpty = true;
                                    break;
                                }
                            }
                        }
                        System.out.println();
                        if (!hasEmpty) {
                            System.out.println("[*] No available stock with empty slots.");
                        } else {
                            System.out.print("[+] Insert Stock number: ");
                            int row;
                            try {
                                row = input.nextInt() - 1;
                            } catch (InputMismatchException e) {
                                System.out.println("[!] Invalid input.");
                                input.nextLine();
                                break;
                            }

                            if (row < 0 || row >= stock.length) {
                                System.out.println("[*] Invalid Stock number.");
                            } else {
                                System.out.println("[*] Stock [" + (row + 1) + "] Details:");
                                boolean rowHasEmpty = false;
                                System.out.print("Stock [" + (row + 1) + "] ==> ");
                                for (int j = 0; j < stock[row].length; j++) {
                                    System.out.print("[" + (j + 1) + " - " + stock[row][j] + "] ");
                                }
                                System.out.println();

                                System.out.print("[*] Catalogue number available: ");
                                for (int j = 0; j < stock[row].length; j++) {
                                    if (stock[row][j].equalsIgnoreCase("Empty")) {
                                        System.out.print((j + 1) + " | ");
                                        rowHasEmpty = true;
                                    }
                                }
                                System.out.println();
                                if (!rowHasEmpty) {
                                    System.out.println("[*] That stock has no empty slots.");
                                } else {
                                    System.out.print("[+] Insert number of catalogue: ");
                                    int col;
                                    try {
                                        col = input.nextInt() - 1;
                                    } catch (InputMismatchException e) {
                                        System.out.println("[!] Invalid input.");
                                        input.nextLine();
                                        break;
                                    }

                                    if (col < 0 || col >= stock[row].length || !stock[row][col].equalsIgnoreCase("Empty")) {
                                        System.out.println("[*] Invalid or non-empty catalogue selected.");
                                    } else {
                                        input.nextLine();
                                        System.out.print("[+] Enter product name: ");
                                        String product = input.nextLine();
                                        stock[row][col] = product;
                                        insertDate[row][col] = LocalDateTime.now();
                                        System.out.println("[*] Product inserted at Stock[" + (row + 1) + "][" + (col + 1) + "]");
                                    }
                                }
                            }
                        }
                    }
                    input.nextLine();
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }
                case 4 -> {
                    System.out.println("-------------- Update Product in Stock --------------");
                    if (!stockInt) {
                        System.out.println("[*] Stock not Set Up yet! Choose Option [1]");
                    } else {
                        System.out.print("Available Stock Number ==> : ");
                        for (int i = 0; i < stock.length; i++) {
                            System.out.print((i + 1) + " | ");
                        }
                        System.out.println();
                        System.out.print("[+] Enter stock number to update: ");
                        int row;
                        try {
                            row = input.nextInt() - 1;
                        } catch (InputMismatchException e) {
                            System.out.println("[!] Invalid input.");
                            input.nextLine();
                            break;
                        }

                        if (row < 0 || row >= stock.length) {
                            System.out.println("[*] Invalid Stock number.");
                        } else {
                            input.nextLine();
                            System.out.print("[+] Enter the product name to update: ");
                            String oldProduct = input.nextLine();

                            boolean found = false;
                            for (int i = 0; i < stock.length; i++) {
                                for (int j = 0; j < stock[i].length; j++) {
                                    if (stock[i][j].equalsIgnoreCase(oldProduct)) {
                                        System.out.print("[*] Stock: [" + oldProduct + "] => Enter new product name: ");
                                        String newProduct = input.nextLine();
                                        stock[i][j] = newProduct;
                                        System.out.println("[*] Product updated successfully.");
                                        found = true;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("[*] Product " + oldProduct + " not found in stock.");
                            }
                        }
                    }
                    input.nextLine();
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }

                case 5 -> {
                    System.out.println("-------------- Delete Product from Stock --------------");
                    if (!stockInt) {
                        System.out.println("[*] Stock not Set Up yet! Choose Option [1]");
                    } else {
                        input.nextLine();
                        System.out.print("[+] Enter the product name to delete: ");
                        String deleteName = input.nextLine();

                        boolean found = false;
                        for (int i = 0; i < stock.length; i++) {
                            for (int j = 0; j < stock[i].length; j++) {
                                if (stock[i][j].equalsIgnoreCase(deleteName)) {
                                    stock[i][j] = "Empty";
                                    insertDate[i][j] = null;
                                    System.out.println("[*] Deleted '" + deleteName + "' from Stock[" + (i + 1) + "][" + (j + 1) + "]");
                                    found = true;
                                }
                            }
                        }
                        if (!found) {
                            System.out.println("[*] Product '" + deleteName + "' not found.");
                        }
                    }
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }

                case 6 -> {
                    System.out.println("[+] Insertion History:");
                    if (!stockInt) {
                        System.out.println("[*] Stock not Set Up yet! Choose Option [1]");
                    } else {
                        boolean hasHistory = false;
                        for (int i = 0; i < stock.length; i++) {
                            for (int j = 0; j < stock[i].length; j++) {
                                if (!stock[i][j].equalsIgnoreCase("Empty") && insertDate[i][j] != null) {
                                    System.out.println("[*] Product: " + stock[i][j] +
                                            " | Date Inserted: " + insertDate[i][j]);
                                    hasHistory = true;
                                }
                            }
                        }
                        if (!hasHistory) {
                            System.out.println("[*] No products have been inserted yet.");
                        }
                    }
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }
                case 7 -> System.out.println("[*] Thank you for using !");
                default -> System.out.println("[!] Invalid option.  choose 1–7.");
            }

        }while(choice != 7);
    }
}
