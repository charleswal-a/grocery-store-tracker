import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

/**
 * This class represents the grocery inventory of a store.
 *
 * @author Charles Walford
 * Solar ID: 116237064
 * Email: charles.walford@stonybrook.edu
 * Assignment number: 6
 * Course: CSE 214
 * Recitation number: 1
 * TAs: Yvette Han, Vincent Zheng
 */
public class HashedGrocery implements Serializable {
    /** Represents the current business day. */
    private int businessDay;
    /** Represents a log of the grocery inventory in the store. */
    private Hashtable<String, Item> groceryTable;

    /**
     * This is a Constructor method that is used to create a new HashedGrocery object.
     */
    public HashedGrocery() {
        groceryTable = new Hashtable<>();
        businessDay = 1;
    }

    /**
     * This method is used to add an item to the grocery inventory.
     *
     * @param item
     * An Item object representing the item to add to the grocery inventory.
     *
     * @throws ItemCodeTakenException
     * when the item code of an Item object to add already exists in the grocery inventory.
     */
    public void addItem(Item item) {
        try {
            if (!groceryTable.containsKey(item.getItemCode())) {
                groceryTable.put(item.getItemCode(), item);
                System.out.println(item.getItemCode() + ": " + item.getName() + " added to inventory");
            } else {
                throw new ItemCodeTakenException("Cannot add item as item code already exists");
            }
        } catch (ItemCodeTakenException e) {
            System.out.println(item.getItemCode() + ": " + e.getMessage());
        }
    }

    /**
     * This method is used to update and item's quantity by a given amount.
     *
     * @param item
     * An Item object representing an item to update.
     * @param adjustByQty
     * An int representing the amount to adjust the item's quantity by.
     */
    public void updateItem(Item item, int adjustByQty) {
        Item i = groceryTable.get(item.getItemCode());
        if (i != null) {
            i.setQtyInStore(i.getQtyInStore() + adjustByQty);
        }
    }

    /**
     * This method is used to add a given item catalog to the grocery inventory.
     *
     * @param filename
     * A String representing the name of the file to read and add to the inventory.
     *
     * @throws IOException
     * when the file name is not a valid file that can be accessed.
     * @throws ParseException
     * when an error occurs while parsing information from the file.
     */
    public void addItemCatalog(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            InputStreamReader input = new InputStreamReader(file);
            JSONParser parser = new JSONParser();
            JSONArray objArr = (JSONArray) parser.parse(input);

            for (Object obj : objArr) {
                JSONObject item = (JSONObject) obj;
                if (item != null) {
                    String itemCode = (String) item.get("itemCode");
                    String name = (String) item.get("itemName");
                    int qtyInStore = Integer.parseInt((String) item.get("qtyInStore"));
                    int averageSalesPerDay = Integer.parseInt((String) item.get("avgSales"));
                    int onOrder = Integer.parseInt((String) item.get("amtOnOrder"));
                    double price = Double.parseDouble((String) item.get("price"));
                    Item i = new Item(itemCode, name, qtyInStore, averageSalesPerDay, onOrder, 0, price);
                    addItem(i);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("\nCould not find file");
        }
    }

    /**
     * This method is used to process sales from a given sales file.
     *
     * @param filename
     * A String representing the name of a file to read and sell from the inventory.
     *
     * @throws IOException
     * when the file name is not a valid file that can be accessed.
     * @throws ParseException
     * when an error occurs while parsing information from the file.
     */
    public void processSales(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            InputStreamReader input = new InputStreamReader(file);
            JSONParser parser = new JSONParser();
            JSONArray objArr = (JSONArray) parser.parse(input);

            for (Object obj : objArr) {
                JSONObject item = (JSONObject) obj;
                System.out.print(item.get("itemCode") + ": ");
                if (groceryTable.containsKey(item.get("itemCode"))) {
                    Item inStore = groceryTable.get(item.get("itemCode"));
                    int qtySold = Integer.parseInt((String) item.get("qtySold"));

                    if (inStore.getQtyInStore() < qtySold) {
                        System.out.print("Not enough stock for sale. Not updated.");
                    } else {
                        updateItem(inStore, -1 * qtySold);
                        System.out.print(qtySold + " units of " + inStore.getName() + " are sold. ");
                        int minQty = 3 * inStore.getAverageSalesPerDay();
                        if (inStore.getQtyInStore() < minQty && inStore.getOnOrder() == 0) {
                            inStore.setOnOrder(2 * inStore.getAverageSalesPerDay());
                            inStore.setArrivalDay(businessDay + 3);
                            System.out.print("Order has been placed for " + inStore.getOnOrder() + " units");
                        }
                    }
                } else {
                    System.out.print("Cannot buy as it is not in the grocery store");
                }
                System.out.println();
            }
            input.close();
        } catch (IOException | ParseException e) {
            System.out.println("\nCould not find file");
        }
    }

    /**
     * This method is used to move to the next business day.
     */
    public void nextBusinessDay() {
        businessDay++;
        System.out.println("Advancing business day...\nBusiness Day " + businessDay + ".\n");
        ArrayList<Item> items = new ArrayList<>();

        for (Item item : groceryTable.values()) {
            if (item.getArrivalDay() == businessDay) {
                items.add(item);
            }
        }

        if (!items.isEmpty()) {
            Collections.sort(items, Comparator.comparing(Item::getItemCode));
            System.out.println("Orders have arrived for:");
            for (Item item : items) {
                System.out.println(item.getItemCode() + ": " + item.getOnOrder() + " units of " + item.getName());
                updateItem(item, item.getOnOrder());
                item.setOnOrder(0);
                item.setArrivalDay(0);
            }
        } else {
            System.out.println("No orders have arrived.");
        }
    }

    /**
     * This method constructs a formatted String containing the information about this HashedGrocery object.
     *
     * @return
     * A String containing the information about this HashedGrocery object.
     */
    public String toString() {
        String output = "\nItem code   Name                Qty   AvgSales   Price    OnOrder    ArrOnBusDay\n" +
                "--------------------------------------------------------------------------------";
        ArrayList<Item> groceryItems = new ArrayList<Item>(groceryTable.values());
        Collections.sort(groceryItems, Comparator.comparing(Item::getItemCode));

        for (Item item : groceryItems) {
            output += "\n" + item.toString();
        }
        return output;
    }

    /**
     * This Getter method is used to get the current business day.
     *
     * @return
     * An int representing the current business day.
     */
    public int getBusinessDay() {
        return businessDay;
    }
}
