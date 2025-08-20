import java.io.*;
import java.util.Scanner;

/**
 * This class represents the Grocery Driver the user can use to interact with the grocery inventory.
 *
 * @author Charles Walford
 * Solar ID: 116237064
 * Email: charles.walford@stonybrook.edu
 * Assignment number: 6
 * Course: CSE 214
 * Recitation number: 1
 * TAs: Yvette Han, Vincent Zheng
 */
public class GroceryDriver {
    /**
     * This a main method that runs the adventure designer and handles user inputs.
     *
     * @param args
     * The command line arguments
     *
     * @throws IOException
     * when the file name is not a valid file that can be accessed.
     * @throws ClassNotFoundException
     * when a class definition can not be found while reading from the grocery save file.
     */
    public static void main(String[] args) {
        boolean quit = false;
        Scanner s = new Scanner(System.in);
        HashedGrocery grocery = new HashedGrocery();

        try {
            FileInputStream file = new FileInputStream("grocery.obj");
            ObjectInputStream input = new ObjectInputStream(file);
            System.out.println("HashedGrocery is loaded from grocery.obj");
            grocery = (HashedGrocery) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Grocery.obj does not exist. Creating new HashedGrocery object...");
        }

        while (!quit) {
            System.out.print("\nBusiness Day " + grocery.getBusinessDay() + ".  \n\n" +
                    "Menu:\n\n" +
                    "(L) Load item catalog\n" +
                    "(A) Add items\n" +
                    "(B) Process Sales\n" +
                    "(C) Display all items\n" +
                    "(N) Move to next business day\n" +
                    "(Q) Quit\n\n" +
                    "Enter option: ");
            String option = s.nextLine().toUpperCase();

            if (option.equals("L")) {
                System.out.print("\nEnter file to load: ");
                option = s.nextLine();
                grocery.addItemCatalog(option);
            } else if (option.equals("A")) {
                System.out.print("\nEnter item code: ");
                String itemCode = s.nextLine();
                System.out.print("Enter item name: ");
                String itemName = s.nextLine();
                System.out.print("Enter quantity in store: ");
                String quantity = s.nextLine();
                System.out.print("Enter average sales per day: ");
                String avgSalesPerDay = s.nextLine();
                System.out.print("Enter price: ");
                String price = s.nextLine();

                if (!isInteger(quantity) || !isInteger(avgSalesPerDay) || !isDouble(price)) {
                    System.out.println("\nInvalid input.");
                } else {
                    grocery.addItem(new Item(itemCode, itemName, Integer.parseInt(quantity), Integer.parseInt(avgSalesPerDay), 0, 0, Double.parseDouble(price)));
                }
            } else if (option.equals("B")) {
                System.out.print("\nEnter filename: ");
                option = s.nextLine();
                grocery.processSales(option);
            } else if (option.equals("C")) {
                System.out.println(grocery);
            } else if (option.equals("N")) {
                grocery.nextBusinessDay();
            } else if (option.equals("Q")) {
                quit = true;
            } else {
                System.out.println("\nInvalid option.");
            }
        }

        try {
            FileOutputStream file = new FileOutputStream("grocery.obj");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(grocery);
            out.close();
            System.out.println("\nHashedGrocery is saved in grocery.obj");
        } catch (IOException e) {
            System.out.println("Error saving grocery data: " + e.getMessage());
        }
        System.out.println("\nProgram terminating normally...");
        s.close();
    }

    /**
     * This method determines if an input is a valid integer
     *
     * @param input
     * The input to determine if it is an integer
     *
     * @return
     * A boolean that represents if input is a valid integer
     *
     * @throws NumberFormatException
     * when the input is not a valid integer
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * This method determines if an input is a valid double
     *
     * @param input
     * The input to determine if it is a double
     *
     * @return
     * A boolean that represents if input is a valid double
     *
     * @throws NumberFormatException
     * when the input is not a valid double
     */
    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
