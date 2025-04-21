package Homework;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Products_System_Management {
    public static void main(String[] args) {
        // Initialize variables
        String[][] stock = new String[100][3]; // [name, quantity, price]
        String[] history = new String[100]; // Insertion history with timestamps
        int productCount = 0;
        int historyCount = 0;
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        // Main program loop
        int choice;
        do {
            // Display menu
            System.out.println("\n"+"=".repeat(62));
            System.out.println("**********|<> Welcome to Stock Management System <>|********** ");
            System.out.println("=".repeat(62)+ "\n");
            System.out.println("1-> Set Up Stock with Catalogue");
            System.out.println("2-> View Product in Stock");
            System.out.println("3-> Insert Product to Stock Catalogue");
            System.out.println("4-> Update Product in Stock Catalogue by Product name");
            System.out.println("5-> Delete Product in Stock Catalogue by Name");
            System.out.println("6-> View Insertion History with Timestamps");
            System.out.println("7-> Exit");

            // Get user choice
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Process choice
            switch (choice) {
                case 1: // Set Up Stock
                    System.out.println("\nSetting up initial stock...");
                    System.out.print("Enter number of products to add: ");
                    int count = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    int i = 0;
                    while (i < count) {
                        System.out.println("\nProduct #" + (i + 1));
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        String quantity = scanner.nextLine();
                        System.out.print("Enter price: ");
                        String price = scanner.nextLine();

                        stock[productCount][0] = name;
                        stock[productCount][1] = quantity;
                        stock[productCount][2] = price;
                        productCount++;

                        history[historyCount++] = "[" + LocalDateTime.now().format(formatter) + "] Added: " + name +
                                " (Qty: " + quantity + ", Price: " + price + ")";
                        i++;
                    }
                    System.out.println("Stock setup completed.");
                    break;

                case 2: // View Products
                    if (productCount == 0) {
                        System.out.println("No products in stock.");
                        break;
                    }

                    System.out.println("\nCurrent Stock:");
                    System.out.println("--------------------------------------------------");
                    System.out.printf("%-20s %-10s %-10s\n", "Product Name", "Quantity", "Price");
                    System.out.println("--------------------------------------------------");

                    for (int j = 0; j < productCount; j++) {
                        System.out.printf("%-20s %-10s $%-10s\n", stock[j][0], stock[j][1], stock[j][2]);
                    }
                    System.out.println("--------------------------------------------------");
                    break;

                case 3: // Insert Product
                    if (productCount >= stock.length) {
                        System.out.println("Stock is full. Cannot add more products.");
                        break;
                    }

                    System.out.println("\nInsert New Product");
                    System.out.print("Enter product name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    String newQuantity = scanner.nextLine();
                    System.out.print("Enter price: ");
                    String newPrice = scanner.nextLine();

                    stock[productCount][0] = newName;
                    stock[productCount][1] = newQuantity;
                    stock[productCount][2] = newPrice;
                    productCount++;

                    history[historyCount++] = "[" + LocalDateTime.now().format(formatter) + "] Added: " + newName +
                            " (Qty: " + newQuantity + ", Price: " + newPrice + ")";
                    System.out.println("Product added successfully.");
                    break;

                case 4: // Update Product
                    if (productCount == 0) {
                        System.out.println("No products in stock to update.");
                        break;
                    }

                    System.out.println("\nUpdate Product");
                    System.out.print("Enter product name to update: ");
                    String updateName = scanner.nextLine();

                    int foundIndex = -1;
                    for (int j = 0; j < productCount; j++) {
                        if (stock[j][0].equalsIgnoreCase(updateName)) {
                            foundIndex = j;
                            break;
                        }
                    }

                    if (foundIndex == -1) {
                        System.out.println("Product not found.");
                        break;
                    }

                    System.out.println("Current details:");
                    System.out.println("Name: " + stock[foundIndex][0]);
                    System.out.println("Quantity: " + stock[foundIndex][1]);
                    System.out.println("Price: " + stock[foundIndex][2]);

                    System.out.println("\nEnter new details (leave blank to keep current value):");
                    System.out.print("New name [" + stock[foundIndex][0] + "]: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("New quantity [" + stock[foundIndex][1] + "]: ");
                    String updatedQuantity = scanner.nextLine();
                    System.out.print("New price [" + stock[foundIndex][2] + "]: ");
                    String updatedPrice = scanner.nextLine();

                    if (!updatedName.isEmpty()) stock[foundIndex][0] = updatedName;
                    if (!updatedQuantity.isEmpty()) stock[foundIndex][1] = updatedQuantity;
                    if (!updatedPrice.isEmpty()) stock[foundIndex][2] = updatedPrice;

                    System.out.println("Product updated successfully.");
                    break;

                case 5: // Delete Product
                    if (productCount == 0) {
                        System.out.println("No products in stock to delete.");
                        break;
                    }

                    System.out.println("\nDelete Product");
                    System.out.print("Enter product name to delete: ");
                    String deleteName = scanner.nextLine();

                    int deleteIndex = -1;
                    for (int j = 0; j < productCount; j++) {
                        if (stock[j][0].equalsIgnoreCase(deleteName)) {
                            deleteIndex = j;
                            break;
                        }
                    }

                    if (deleteIndex == -1) {
                        System.out.println("Product not found.");
                        break;
                    }

                    for (int j = deleteIndex; j < productCount - 1; j++) {
                        stock[j][0] = stock[j + 1][0];
                        stock[j][1] = stock[j + 1][1];
                        stock[j][2] = stock[j + 1][2];
                    }

                    productCount--;
                    System.out.println("Product deleted successfully.");
                    break;

                case 6: // View Insertion History
                    if (historyCount == 0) {
                        System.out.println("No insertion history available.");
                        break;
                    }

                    System.out.println("\nInsertion History with Timestamps:");
                    System.out.println("--------------------------------------------------");
                    System.out.printf("%-20s %-50s\n", "Timestamp", "Action");
                    System.out.println("--------------------------------------------------");

                    for (int j = 0; j < historyCount; j++) {
                        System.out.println(history[j]);
                    }
                    System.out.println("--------------------------------------------------");
                    break;

                case 7: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}