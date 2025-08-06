package Shahaf_Einav;


public class Item implements Cloneable {
    public enum Category {Kids, Electricity, Office, Cloths}

    protected Category category;
    private static int counter;
    protected String name;
    protected double price;
    protected int serialNumber;

    public Item(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.serialNumber = ++counter;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public static int getNumOfProducts() {
        return counter;
    }

    public Category getCategory() {
        return category;
    }

    protected Object clone() throws CloneNotSupportedException {
        Item cloned = (Item) super.clone();
        return cloned;
    }

    public String toString() {
        return "Item{name='" + name + "', price=" + price + '}' + "',category=" + category;
    }



}


