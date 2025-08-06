package Shahaf_Einav;

import java.util.List;

public class ManagerClass {
    private Buyers[] buyers;
    private int logicalSizeBuyer;
    private int physicalSizeBuyer;

    private Sellers[] sellers;
    private int logicalSizeSeller;
    private int physicalSizeSeller;
    private int indexBuyer;
    private int indexSeller;


    public ManagerClass() {
        this.physicalSizeBuyer = 2;
        this.physicalSizeSeller = 2;
        this.sellers = new Sellers[physicalSizeSeller];
        this.buyers = new Buyers[physicalSizeBuyer];
        this.logicalSizeBuyer = 0;
        this.logicalSizeSeller = 0;
        this.indexBuyer = 0;
        this.indexSeller = 0;
    }

    public Buyers[] getBuyers() {
        return buyers;
    }

    public int getLogicalSizeBuyer() {
        return logicalSizeBuyer;
    }

    public int getPhysicalSizeBuyer() {
        return physicalSizeBuyer;
    }

    public Sellers[] getSellers() {
        return sellers;
    }

    public int getLogicalSizeSeller() {
        return logicalSizeSeller;
    }

    public int getPhysicalSizeSeller() {
        return physicalSizeSeller;
    }

    public int getIndexBuyer() {
        return indexBuyer;
    }

    public int getIndexSeller() {
        return indexSeller;
    }

    public void addSeller(String sellerName, String sellerPassword) {
        sellers = checkIfLogicalEqualsPhysicalSeller();
        sellers[logicalSizeSeller++] = new Sellers(sellerName, sellerPassword);
    }

    public void addBuyer(String buyerName, String buyerPassword, String addressStreet, int addressNumber, String addressCity, String addressCountry) {
        buyers = checkIfLogicalEqualsPhysicalBuyer();
        buyers[logicalSizeBuyer++] = new Buyers(buyerName, buyerPassword, addressStreet, addressNumber, addressCity, addressCountry);

    }

    public static boolean checkIfExistSellers(String sellerName, Sellers[] names, int logicalSizeSeller) {
        for (int i = 0; i < logicalSizeSeller; i++) {
            if (names[i].getSellerName().equals(sellerName)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemsExistInCategory(Item.Category category) {
        for (Sellers seller : sellers) {
            if (seller != null) {
                for (int i = 0; i < seller.getLogicalSizeItemSellerArray(); i++) {
                    if (seller.getCategory(i) == category) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static boolean checkIfExistBuyers(String buyerName, Buyers[] names, int logicalSizeBuyer) {
        for (int i = 0; i < logicalSizeBuyer; i++) {
            if (names[i].getBuyerName().equals(buyerName)) {
                return true;
            }
        }
        return false;
    }


    public Sellers[] checkIfLogicalEqualsPhysicalSeller() {
        if (logicalSizeSeller >= physicalSizeSeller) {
            Sellers[] newSellers = new Sellers[sellers.length * 2];
            for (int i = 0; i < sellers.length; i++) {
                newSellers[i] = sellers[i];
            }
            return newSellers;

        } else return sellers;
    }

    public Buyers[] checkIfLogicalEqualsPhysicalBuyer() {
        if (logicalSizeBuyer >= physicalSizeBuyer) {
            Buyers[] newBuyers = new Buyers[buyers.length * 2];
            for (int i = 0; i < buyers.length; i++) {
                newBuyers[i] = buyers[i];
            }
            return newBuyers;

        } else return buyers;
    }

    public void printItemsByCategory(Item.Category category) {
        for (int i = 0; i < logicalSizeSeller; i++) {
            Sellers seller = sellers[i];
            List<Item> itemsInCategory = seller.getItemsByCategory(category);
            System.out.println("Seller: " + seller.getSellerName());
            for (Item item : itemsInCategory) {
                System.out.println("Item: " + item.getName() + ", Price: " + item.getPrice());
            }
        }
}
}


