package Shahaf_Einav;

import java.time.LocalDate;

public class Buyers implements Comparable<Buyers> {
    private String buyerName;
    private String buyerPassword;
    private Address address;
    private PurchaseHistory[] shoppingCartHistory; // array that contains the shopping carts
    private int logicalSizeHistory;
    private ShoppingCart shoppingCart;

    public Buyers(String buyerName, String buyerPassword, String addressStreet, int addressNumber, String addressCity, String addressCountry) {
        this.buyerName = buyerName;
        this.buyerPassword = buyerPassword;
        this.address = new Address(addressStreet, addressNumber, addressCity, addressCountry);
        this.shoppingCart = new ShoppingCart();
        this.shoppingCartHistory = new PurchaseHistory[2];
        this.logicalSizeHistory = 0;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public PurchaseHistory getSpecificShoppingCartHistory(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index <= logicalSizeHistory) {
            return shoppingCartHistory[index];
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerPassword() {
        return buyerPassword;
    }

    public String getAddressStreet() {
        return address.getAddressStreet();
    }

    public int getAddressNumber() {
        return address.getAddressNumber();
    }

    public String getAddressCity() {
        return address.getAddressCity();
    }

    public String getAddressCountry() {
        return address.getAddressCountry();
    }

    public String getItemName(int index) {
        if (index >= 0 && index < shoppingCart.getLogicalSizeItemsInCart()) {
            return shoppingCart.getItemName(index);
        }
        return null;
    }

    public double getItemPrice(int index) {
        if (index >= 0 && index < shoppingCart.getLogicalSizeItemsInCart()) {
            return shoppingCart.getItemPrice(index);
        }
        return -1;
    }

    public int getLogicalSizeHistory() {
        return logicalSizeHistory;
    }

    public int getLogicalSizeItemsInCart() {
        return shoppingCart.getLogicalSizeItemsInCart();
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerPassword(String buyerPassword) {
        this.buyerPassword = buyerPassword;
    }

    public void setAddressStreet(String addressStreet) {
        this.address.setAddressStreet(addressStreet);
    }

    public void setAddressNumber(int addressNumber) {
        this.address.setAddressNumber(addressNumber);
    }

    public void setAddressCity(String addressCity) {
        this.address.setAddressCity(addressCity);
    }

    public void setAddressCountry(String addressCountry) {
        this.address.setAddressCountry(addressCountry);
    }

    public void setLogicalSize(int logicalSize) {
        this.shoppingCart.setLogicalSizeItemsInCart(logicalSize);
    }

    public void setItemName(int index, String itemName) {
        if (index >= 0 && index < shoppingCart.getLogicalSizeItemsInCart()) {
            shoppingCart.setItemName(itemName, index);
        }
    }

    public void setItemPrice(int index, double price) {
        if (index >= 0 && index < shoppingCart.getLogicalSizeItemsInCart()) {
            shoppingCart.setItemPrice(price, index);
        }
    }

    public void setShoppingCart(ShoppingCart cart) {
        this.shoppingCart.setLogicalSizeItemsInCart(cart.getLogicalSizeItemsInCart());
        this.shoppingCart = cart;
    }





    public void todayDate() {
        LocalDate today = LocalDate.now();
        System.out.println("purchase date: " + today);
    }

    private void expandShoppingCartHistory() {
        PurchaseHistory[] newHistory = new PurchaseHistory[shoppingCartHistory.length * 2];
        for (int i = 0; i < shoppingCartHistory.length; i++) { // only for existing places with values
            newHistory[i] = shoppingCartHistory[i];
        }
        shoppingCartHistory = newHistory;
    }

    public Double checkOutShoppingCart() {
        double totalSum = shoppingCart.checkOutShoppingCart();
        return totalSum;
    }

    public void addShoppingCartHistory(double payPrice) {
        if (logicalSizeHistory >= shoppingCartHistory.length) {
            expandShoppingCartHistory();
        }

        try {
            ShoppingCart[] itemsCopy = new ShoppingCart[1];
            itemsCopy[0] = (ShoppingCart) shoppingCart.clone(); // Clone the shopping cart

            PurchaseHistory history = new PurchaseHistory(itemsCopy, payPrice, LocalDate.now());
            shoppingCartHistory[logicalSizeHistory] = history;
            shoppingCart.resetShoppingCart();
            logicalSizeHistory++;
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported: " + e.getMessage());
        }
    }


    public void addItem(String itemName, double itemPrice, Item.Category category) {
        shoppingCart.addItem(itemName, itemPrice, category);
    }

    public void addItemDelux(String itemName, double itemPrice, Item.Category category, String deluxPackage, double extraPrice) {
        shoppingCart.addItemDelux(itemName, itemPrice, category, deluxPackage, extraPrice);
    }



    public void printShoppingCartHistory() {
        for (int i = 0; i < logicalSizeHistory; i++) {
            PurchaseHistory history = shoppingCartHistory[i];
            System.out.println("Purchase " + (i + 1) + ":");
            System.out.println("Date: " + history.getPurchaseDate());
            for (ShoppingCart cart : history.getShoppingCarts()) {
                for (int j = 0; j < cart.getLogicalSizeItemsInCart(); j++) {
                    Item item = cart.getItem(j);
                    if (item instanceof DeluxItem) {
                        DeluxItem deluxItem = (DeluxItem) item;
                        System.out.println(" - " + deluxItem.getName() + ": " + deluxItem.getPrice() +
                                ", Deluxe Package: " + deluxItem.getDelux() +
                                ", Extra Price: " + deluxItem.getExtraPrice());
                    } else {
                        System.out.println(" - " + item.getName() + ": " + item.getPrice());
                    }
                }
            }
            System.out.println(" ");
        }
    }

    public void printItems() {
        for (int i = 0; i < shoppingCart.getLogicalSizeItemsInCart(); i++) {
            System.out.print("Item " + (i + 1) + ": " + shoppingCart.getItemName(i) + ", Price: " + shoppingCart.getItemPrice(i));
            Item item = shoppingCart.getItems()[i];
            if (item instanceof DeluxItem) {
                DeluxItem deluxItem = (DeluxItem) item;
                System.out.print(", Deluxe Package: " + deluxItem.getDelux() + ", Extra Price: " + deluxItem.getExtraPrice());
            }
            System.out.println();
        }
    }


    public int compareTo(Buyers o) {
        return this.buyerName.compareTo(o.buyerName);
    }

    public String toString() {
        return "Buyer Information:\n" +
                "------------------\n" +
                "Name: " + buyerName + "\n" +
                "Password: " + buyerPassword + "\n" +
                "Address:\n" + address.toString();
    }
}
