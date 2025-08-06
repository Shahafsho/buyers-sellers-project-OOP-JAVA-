package Shahaf_Einav;

import java.util.Arrays;

public class ShoppingCart implements Cloneable {
    private Item[] items;
    private int logicalSizeItemsInCart; // Keeps track of the number of items added to cart

    public ShoppingCart() {
        this.items = new Item[2];
        this.logicalSizeItemsInCart = 0;
    }

    public void addItem(String itemName, double itemPrice, Item.Category category) {
        if (logicalSizeItemsInCart >= items.length) {
            // Resize the array if it's full
            resizeArray();
        }
        items[logicalSizeItemsInCart++] = new Item(itemName, itemPrice, category);
    }

    public void addItemDelux(String itemName, double itemPrice, Item.Category category, String deluxPackage, double extraPrice) {
        if (logicalSizeItemsInCart >= items.length) {
            resizeArray();
        }
        items[logicalSizeItemsInCart++] = new DeluxItem(itemName, itemPrice, category, deluxPackage, extraPrice);
    }


    public void setLogicalSizeItemsInCart(int logicalSizeItemsInCart) {
        this.logicalSizeItemsInCart = logicalSizeItemsInCart;
    }

    public void setItemName(String name, int index) {
        this.items[index].setName(name);
    }

    public void setItemPrice(double price, int index) {
        this.items[index].setPrice(price);
    }

    public String getItemName(int index) {
        return this.items[index].getName();
    }

    public double getItemPrice(int index) {
        return this.items[index].getPrice();
    }

    public int getLogicalSizeItemsInCart() {
        return logicalSizeItemsInCart;
    }

    public void resizeArray() {
        Item[] newShoppingCart = new Item[items.length * 2];
        System.arraycopy(items, 0, newShoppingCart, 0, items.length);
        items = newShoppingCart;
    }

    public void resetShoppingCart() {
        this.items = new Item[2];
        logicalSizeItemsInCart = 0;
    }




    public double checkOutShoppingCart() {
        double totalSum = 0;
        for (int i = 0; i < logicalSizeItemsInCart; i++) {
            if (items[i] != null) {
                totalSum += items[i].getPrice();
                if (items[i] instanceof DeluxItem) {
                    totalSum += ((DeluxItem) items[i]).getExtraPrice();
                }
            }
        }
        return totalSum;
    }

    public Item getItem(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < logicalSizeItemsInCart) {
            return items[index];
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }




    public Item[] itemsCopy() {
        Item[] itemsCopy = new Item[logicalSizeItemsInCart];
        System.arraycopy(items, 0, itemsCopy, 0, logicalSizeItemsInCart);
        return itemsCopy;
    }

    public Item[] getItems() {
        return Arrays.copyOf(items, logicalSizeItemsInCart);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ShoppingCart cloned = (ShoppingCart) super.clone();
        cloned.items = new Item[this.items.length];
        for (int i = 0; i < this.logicalSizeItemsInCart; i++) {
            cloned.items[i] = (Item) this.items[i].clone();
        }
        return cloned;
    }


}
