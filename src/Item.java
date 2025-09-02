import java.io.Serializable;

/**
 * This class represents an item that is a part of the grocery inventory.
 *
 * @author Charles Walford
 */
public class Item implements Serializable {
    /** Represents the code unique to this item. */
    private String itemCode;
    /** Represents the name of this item. */
    private String name;
    /** Represents the quantity of this item in the store. */
    private int qtyInStore;
    /** Represents average sales per day of this item. */
    private int averageSalesPerDay;
    /** Represents the quantity of this item ordered and on the way. */
    private int onOrder;
    /** Represents the day that the next order will arrive. */
    private int arrivalDay;
    /** Represents the price of this item. */
    private double price;

    /**
     * This is a Constructor method that is used to create a new Item object.
     */
    public Item() {
        this.itemCode = "";
        this.name = "";
        this.qtyInStore = 0;
        this.averageSalesPerDay = 0;
        this.onOrder = 0;
        this.arrivalDay = 0;
        this.price = 0;
    }

    /**
     * This is a Constructor method that is used to create a new Item object with parameters.
     *
     * @param itemCode
     * The unique code of this item.
     * @param name
     * The name of this item.
     * @param qtyInStore
     * The quantity of this item in the store.
     * @param averageSalesPerDay
     * The average sales per day of this item.
     * @param onOrder
     * The quantity of this item ordered.
     * @param arrivalDay
     * The arrival day of the ordered item.
     * @param price
     * The price of the item.
     */
    public Item(String itemCode, String name, int qtyInStore, int averageSalesPerDay, int onOrder, int arrivalDay, double price) {
        this.itemCode = itemCode;
        this.name = name;
        this.qtyInStore = qtyInStore;
        this.averageSalesPerDay = averageSalesPerDay;
        this.onOrder = onOrder;
        this.arrivalDay = arrivalDay;
        this.price = price;
    }

    /**
     * This Getter method is used to get the item code.
     *
     * @return
     * A String representing the unique code for this item.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * This Setter method is used to set the item code.
     *
     * @param itemCode
     * A String representing the unique code for this item.
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * This Getter method is used to get the item name.
     *
     * @return
     * A String representing the name of this item.
     */
    public String getName() {
        return name;
    }

    /**
     * This Getter method is used to get the quantity of this item in store.
     *
     * @return
     * An int representing the quantity of this item in store.
     */
    public int getQtyInStore() {
        return qtyInStore;
    }

    /**
     * This Setter method is used to set the quantity of this item in store.
     *
     * @param qtyInStore
     * An int representing the quantity of this item in store.
     */
    public void setQtyInStore(int qtyInStore) {
        this.qtyInStore = qtyInStore;
    }

    /**
     * This Getter method is used to get the average sales per day of this object.
     *
     * @return
     * An int representing the average sales per day of this object.
     */
    public int getAverageSalesPerDay() {
        return averageSalesPerDay;
    }

    /**
     * This Getter method is used to get the quantity of items on order.
     *
     * @return
     * An int representing the quantity of items on order.
     */
    public int getOnOrder() {
        return onOrder;
    }

    /**
     * This Setter method is used to set the quantity of items on order.
     *
     * @param onOrder
     * An int representing the quantity of items on order.
     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    /**
     * This Getter method is used to get the arrival day of the order.
     *
     * @return
     * An int representing the arrival day of the order.
     */
    public int getArrivalDay() {
        return arrivalDay;
    }

    /**
     * This Setter method is used to set the arrival day of the order.
     *
     * @param arrivalDay
     * An int representing the arrival day fo the order.
     */
    public void setArrivalDay(int arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    /**
     * This method constructs a formatted String containing the information about this Item object.
     *
     * @return
     * A String containing the information about this Item object.
     */
    public String toString() {
        return String.format("%-12s%-21s%-11d%-10d%-10.2f%-10d%d", itemCode, name, qtyInStore, averageSalesPerDay, price, onOrder, arrivalDay);
    }
}
