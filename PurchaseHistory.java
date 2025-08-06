package Shahaf_Einav;

import java.time.LocalDate;

public class PurchaseHistory implements Cloneable {
    private ShoppingCart[] shoppingCarts;
    private double totalPrice;
    private LocalDate purchaseDate;

    public PurchaseHistory(ShoppingCart[] shoppingCarts, double totalPrice, LocalDate purchaseDate) {
        this.shoppingCarts = shoppingCarts;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
    }

    public ShoppingCart[] getShoppingCarts() {
        return shoppingCarts;
    }

    public ShoppingCart getShoppingCartByIndex(int i){
        return shoppingCarts[i];
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Item[] getItemsFromCart(int index) {
        if (index >= 0 && index < shoppingCarts.length) {
            return shoppingCarts[index].getItems(); // Assuming getItems() returns Item[]
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PurchaseHistory cloned = (PurchaseHistory) super.clone();
        cloned.shoppingCarts = new ShoppingCart[shoppingCarts.length];
        for (int i = 0; i < shoppingCarts.length; i++) {
            cloned.shoppingCarts[i] = (ShoppingCart) shoppingCarts[i].clone();
        }
        return cloned;
    }
}
