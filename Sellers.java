package Shahaf_Einav;

import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.List;

public class Sellers implements Comparable<Sellers>{

    private String sellerName;
    private String sellerPassword;
    private Item[] sellerItems;
    private int logicalSize;

    public Sellers(String sellerName, String sellerPassword) {
        this.sellerName = sellerName;
        this.sellerPassword = sellerPassword;
        this.sellerItems = new Item[2];
        this.logicalSize = 0;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
    }

    public String getItemName(int index) {
        if (index >= 0 && index < logicalSize) {
            return sellerItems[index].getName();
        }
        return null;
    }

    public double getItemPrice(int index) {
        if (index >= 0 && index < logicalSize) {
            return sellerItems[index].getPrice();
        }
        return -1;
    }
    public Item.Category getCategory(int index) {
        return sellerItems[index].getCategory();
    }

    public List<Item> getItemsByCategory(Item.Category category) {
        List<Item> itemsInCategory = new ArrayList<Item>();
        for (int i = 0; i < logicalSize; i++) {
            if (sellerItems[i].getCategory() == category) {
                itemsInCategory.add(sellerItems[i]);
            }
        }
        return itemsInCategory;
    }
    public int getLogicalSizeItemSellerArray() {
        return logicalSize;
    }

    public Item getItem(int index) {
        return sellerItems[index];
    }

    public void addItemDelux(String itemName, double itemPrice,Item.Category category,String deluxPackege,double extraPrice) {
        if (logicalSize >= sellerItems.length) {
            resizeArray();
        }
        sellerItems[logicalSize] = new DeluxItem(itemName, itemPrice, category, deluxPackege, extraPrice);
        logicalSize++;

    }

    public void addItem(String itemName, double itemPrice,Item.Category category) {
        if (logicalSize >= sellerItems.length) {
            resizeArray();
        }
            sellerItems[logicalSize] = new Item(itemName, itemPrice, category);
            logicalSize++;

    }

    private void resizeArray() {
        Item[] newSellerItems = new Item[sellerItems.length * 2];
        for (int i = 0; i < sellerItems.length; i++) {
            newSellerItems[i] = sellerItems[i];
        }
        sellerItems = newSellerItems;
    }

    public void printItems() {
        for (int i = 0; i < logicalSize; i++) {
            Item item = sellerItems[i];
            System.out.println("Item " + (i + 1) + ": " + item.getName() + ", Price: " + item.getPrice());
            if (item instanceof DeluxItem) {
                DeluxItem deluxItem = (DeluxItem) item;
                System.out.println("The product has special packaging: " + deluxItem.getDelux() + "\n" +
                        "The packaging price is: " + deluxItem.getExtraPrice());
            }
        }
    }


    public int compareTo(Sellers o) {
        if(this.logicalSize < o.logicalSize)return -1;
        else if(this.logicalSize > o.logicalSize)return 1;
        return 0;
    }
    public String toString() {
        return "Seller name: " + sellerName + ", Seller password: " + sellerPassword;
    }

}
