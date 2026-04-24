package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStoreApp
{
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Product> products;
    static ArrayList<Product> shoppingCart;

    static void main()
    {
        // make sure all products are loaded before we display the home screen
        products = loadProducts();

        // start the application
        displayHomeScreen();
    }

    static void displayHomeScreen()
    {
        while(true)
        {
            System.out.println();
            System.out.println("Welcome to my Store!");
            System.out.println("---------------------------------");
            System.out.println();
            System.out.println("D) Display Products");
            System.out.println("C) Display Cart");
            System.out.println("X) Exit");
            System.out.print("Make a selection: ");
            String choice = scanner.nextLine().toUpperCase().strip();

            System.out.println();

            switch (choice)
            {
                case "D":
                    System.out.println("Create the Product Search Screen");
                    break;
                case "C":
                    System.out.println("Create the Display Cart Screen");
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    // return exits the loop and the method, which in this case ends the application
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    static ArrayList<Product> loadProducts()
    {
        // 1. create the new ArrayList
        ArrayList<Product> products = new ArrayList<>();

        // 2. populate the list
        try
        {
            FileReader fileReader = new FileReader("data/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine(); // read the header line and ignore it
            line = bufferedReader.readLine();
            while ((line != null))
            {
                String[] productData = line.split("\\|");
                String sku = productData[0];
                String productName = productData[1];
                double price = Double.parseDouble(productData[2]);
                String department = productData[3];

                // create a product object and add it to the list
                Product product = new Product(sku, productName, price, department);

                // add the current product to the list of products
                products.add(product);

                // read the next line
                line = bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        // load all products from the "data/products.csv" file here


        // 3. return the ArrayList
        return products;
    }
}