
// Einav Hillel, id: 211394754, Teacher: Keren
// Shahaf Shohat, id: 209234053, Teacher: Keren

package Shahaf_Einav;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Shahaf_Einav {

    public static void main(String[] args) {
        ManagerClass manager = new ManagerClass();
        Scanner sc = new Scanner(System.in);
        menu(manager, sc);
    }

    public static void menu(ManagerClass manager, Scanner sc) {
        while (true) {
            System.out.println(" ");
            printMenu();
            System.out.println("Choose an option:");
            try {
                int answer = sc.nextInt();
                sc.nextLine();

                switch (answer) {
                    case 0 -> {
                        System.out.println("Exiting the program.");
                        sc.close();
                        return;
                    }
                    case 1 -> addSellerInput(manager, sc);
                    case 2 -> addBuyerInput(manager, sc);
                    case 3 -> addItemToSeller(manager, sc);
                    case 4 -> addItemToBuyerShoppingCart(manager, sc);
                    case 5 -> CheckingOutBuyersCart(manager, sc);
                    case 6 -> printBuyerData(manager, sc);
                    case 7 -> printSellerData(manager, sc);
                    case 8 -> printByCategory(manager, sc);
                    case 9 -> createShoppingCartFromHistory(manager, sc);
                    default -> System.out.println("Invalid option. Please type again...");
                }
            }
        catch (InputMismatchException e){
            System.out.println("Error: Invalid input. Please enter a valid number.");
            sc.nextLine();}
        catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());}
    }
}

    public static void printMenu() {
        System.out.println("Hello and welcome to our digital store. ");
        System.out.println("To exit the program, Please type 0: ");
        System.out.println("To add a new seller to our platform, Please type 1: ");
        System.out.println("To add a new buyer to our platform, Please type 2: ");
        System.out.println("To add item to a seller's store, Please type 3: ");
        System.out.println("To add item to a buyer's shopping card, Please type 4: ");
        System.out.println("To check out a buyer's cart, Please type 5: ");
        System.out.println("To show all buyer's data, Please type 6: ");
        System.out.println("To show all seller's data, Please type 7: ");
        System.out.println("To show all products per category, Please type 8: ");
        System.out.println("To create new shopping cart from shopping cart history, Please type 9: ");

    }


    public static void printArraySellers(Sellers[] array, int logicalSizeSeller) {
        for (int i = 0; i < logicalSizeSeller; i++) {
            System.out.println((i + 1) + ")" + array[i].getSellerName());
        }
    }

    public static void printArrayBuyers(Buyers[] array, int logicalSizeBuyer) {
        for (int i = 0; i < logicalSizeBuyer; i++) {
            System.out.println((i + 1) + ")" + array[i].getBuyerName());
        }
    }

    public static void printArrayBuyersData(Buyers[] array, int logicalSizeBuyer) {
        Arrays.sort(array, 0, logicalSizeBuyer, new Comparator<Buyers>() {
            public int compare(Buyers b1, Buyers b2) {
                return b1.getBuyerName().compareTo(b2.getBuyerName());
            }
        });
        for (int i = 0; i < logicalSizeBuyer; i++) {
            System.out.println(" ");
            System.out.println("------------------");
            System.out.println((i + 1) + ")" + array[i].toString());
            if (array[i].getLogicalSizeItemsInCart() == 0) {
                System.out.println("There are no items in: " + "'" + array[i].getBuyerName() + "'" + " Shopping cart.");
            } else {
                System.out.println("The buyer's current Shopping cart: ");
                array[i].printItems();
            }
            if (array[i].getLogicalSizeHistory() == 0) {
                System.out.println("'" + array[i].getBuyerName() + "'" + " Shopping cart history is empty, Please checkout your cart first. ");
            } else {
                System.out.println("Here is " + "'" + array[i].getBuyerName() + "'" + " Shopping cart history: ");
                array[i].printShoppingCartHistory();
            }
        }
    }

    public static void printArraySellersData(Sellers[] array, int logicalSizeSeller) {
        Arrays.sort(array, 0, logicalSizeSeller, new Comparator<Sellers>() {
            public int compare(Sellers s1, Sellers s2) {
                return s2.getLogicalSizeItemSellerArray() - (s1.getLogicalSizeItemSellerArray());
            }
        });
        for (int i = 0; i < logicalSizeSeller; i++) {
            System.out.println("------------------");
            System.out.println((i + 1) + ")" + array[i].toString());
            if (array[i].getLogicalSizeItemSellerArray() == 0) {
                System.out.println("The Seller has no items available for sale at this moment.");
                System.out.println("------------------");
            } else {
                System.out.println("The Seller's items available for sale: ");
                array[i].printItems();
                System.out.println("------------------");
                System.out.println(" ");
            }
        }
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static int inputCheckIfValidInt(int logicalSize, Scanner sc) {
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                if (input > 0 && input <= logicalSize)
                    return input;
                else
                    throw new IllegalArgumentException("Invalid input, choose a number between 1 - " + logicalSize);
            } 
            catch (InputMismatchException e) {
                System.out.print("Error: Invalid input. Please enter a valid number. \nYour choice: ");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.print("Error: " + e.getMessage() + "\nYour choice: ");
                sc.nextLine();
            }
        }
    }


    public static String inputCheckIfValidString(Scanner sc) {
        String input = "";
        while (true) {
            try {
                input = sc.nextLine();
                if (!input.isEmpty()) {
                    return input;
                } else {
                    throw new IllegalArgumentException("Invalid input. Must contain at least one letter.");
                }
            } catch (InputMismatchException e) {
                System.out.print("Error: Invalid input. Please enter a valid string. \nYour choice: ");
            } catch (IllegalArgumentException e) {
                System.out.print("Error: " + e.getMessage() + "\nYour choice: ");
            }
        }
    }

    public static double inputCheckIfValidDouble(Scanner sc) {
        double input;
        while (true) {
            try {
                input = Double.parseDouble(sc.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Error: Invalid input. Please enter a valid number. \nYour choice: ");
            }
        }
    }



    public static void addSellerInput(ManagerClass manager, Scanner sc) {
        String sellerName = "";
        int attempts = 0;
        boolean nameTaken = false;
        System.out.println("Adding new seller to the platform.");

        do {
            if (attempts >= 5) {
                System.out.println("Maximum attempts reached. No seller added.");
                break;
            }
            try {
                System.out.println("Please enter the name of the Seller (Alphabetic letters, numbers, and symbols only): ");
                sellerName = inputCheckIfValidString(sc);
                nameTaken = manager.checkIfExistSellers(sellerName, manager.getSellers(), manager.getLogicalSizeSeller());
                if (nameTaken && attempts < 4) {
                    System.out.println("The seller nickname already exists, you have " + (4 - attempts) + " tries left. Please choose another seller nickname.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
            attempts++;
        } while (nameTaken);

        if (attempts < 5) {
            System.out.println("Please enter the password you want for this seller: ");
            String sellerPassword = inputCheckIfValidString(sc);

            manager.addSeller(sellerName, sellerPassword);
            System.out.println("You have been successfully added as a Seller. Your Seller number is: " + manager.getLogicalSizeSeller());
        }
    }



        public static void addBuyerInput(ManagerClass manager, Scanner sc) {
        String buyerName = "";
        int attempts = 0;
        boolean nameTaken = false;
        System.out.println("Adding new buyer to the platform.");

        do {
            if (attempts >= 5) {
                System.out.println("Maximum attempts reached. No buyer added.");
                return;
            }

            try {
                System.out.println("Please enter the name of the Buyer (Alphabetic letters, numbers, and symbols only): ");
                buyerName = inputCheckIfValidString(sc);

                nameTaken = manager.checkIfExistBuyers(buyerName, manager.getBuyers(), manager.getLogicalSizeBuyer());
                if (nameTaken && attempts < 4) {
                    System.out.println("The buyer nickname already exists, you have " + (4 - attempts) + " tries left. Please choose another buyer nickname.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input format. Please enter a valid name.");
                sc.nextLine();
            }

            attempts++;
        } while (nameTaken);

        if (attempts < 5) {
            String buyerPassword;
            String addressStreet;
            int addressNumber;
            String addressCity;
            String addressCountry;

            System.out.println("Please enter the password: ");
            buyerPassword = inputCheckIfValidString(sc);


            System.out.println("Please enter the address street: ");
            addressStreet = inputCheckIfValidString(sc);


            System.out.println("Please enter the street number: ");
            addressNumber = inputCheckIfValidInt(1000, sc); // street number possibly could not be greater than 1000
            sc.nextLine();

            System.out.println("Please enter the address city: ");
            addressCity = inputCheckIfValidString(sc);


            System.out.println("Please enter the address country: ");
            addressCountry = inputCheckIfValidString(sc);

            manager.addBuyer(buyerName, buyerPassword, addressStreet, addressNumber, addressCity, addressCountry);
            System.out.println("You have been successfully added as a buyer. Your buyer number is: " + manager.getLogicalSizeBuyer());
        }
    }




public static void addItemToSeller(ManagerClass manager, Scanner sc) {
    String itemName;
    double itemPrice;
    int indexSeller;
    String deluxPackage = null;
    char delux = 'A';
    double extraPrice = 0;
    Item.Category category;

    System.out.println("Adding item to a seller's store.");

    if (manager.getLogicalSizeSeller() > 0) {
        printArraySellers(manager.getSellers(), manager.getLogicalSizeSeller());
        System.out.println("Please enter the index of the seller you would like to add item for: ");
        indexSeller = sc.nextInt();

        while (indexSeller < 1 || indexSeller > manager.getLogicalSizeSeller()) {
            System.out.println("The number of seller doesn't exist... ");
            printArraySellers(manager.getSellers(), manager.getLogicalSizeSeller());
            System.out.println("Please enter the number of the seller you would like to add item for: ");
            indexSeller = sc.nextInt();
        }
        sc.nextLine();
        System.out.println("The chosen Seller is: " + "'" + manager.getSellers()[indexSeller - 1].getSellerName() + "'");
    } else {
        System.out.println("There are no sellers in the platform... Please add seller first.");
        return;
    }

    System.out.println("Please enter the name of the product: ");
    itemName = inputCheckIfValidString(sc);
    System.out.println("Please enter the price of the product: ");
    itemPrice = inputCheckIfValidDouble(sc);

    while (true) {
        System.out.println("Please enter the category of the product: (Kids, Electricity, Office, Cloths) ");
        String categoryInput = inputCheckIfValidString(sc);
        categoryInput = capitalizeFirstLetter(categoryInput);

        try {
            category = Item.Category.valueOf(categoryInput);
            break; // Exit the loop if category is valid
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category entered. Please try again:");
        }
    }

    System.out.println("Would you like to add delux package to the seller store? Y/N");
    delux = sc.next().charAt(0);
    delux = Character.toUpperCase(delux);
    sc.nextLine();
    if (delux == 'Y') {
        System.out.println("Please enter the type of the delux package you want for this item: ");
        deluxPackage = inputCheckIfValidString(sc);
        System.out.println("Please enter the price of the delux package: ");
        extraPrice = inputCheckIfValidDouble(sc);
        manager.getSellers()[indexSeller - 1].addItemDelux(itemName, itemPrice, category, deluxPackage, extraPrice);
    } else {
        manager.getSellers()[indexSeller - 1].addItem(itemName, itemPrice, category);
    }

    int lastItemIndex = manager.getSellers()[indexSeller - 1].getLogicalSizeItemSellerArray() - 1;
    Item lastItem = manager.getSellers()[indexSeller - 1].getItem(lastItemIndex);
    System.out.println("The name of the product is: " + lastItem.getName() + "\n" +
            "The price of the product is: " + lastItem.getPrice() + "\n" + "The category of the product is: " + lastItem.getCategory());
    if (lastItem instanceof DeluxItem) {
        DeluxItem deluxItem = (DeluxItem) lastItem;
        System.out.println("The product has special packaging: " + deluxItem.getDelux() + "\n" +
                "The packaging price is: " + deluxItem.getExtraPrice());
    }
}




    public static void addItemToBuyerShoppingCart(ManagerClass manager, Scanner sc) {
        int indexBuyer;
        int indexSeller;
        int buyerNumber = 0;
        int sellerNumber = 0;
        int indexItemFromSeller;

        System.out.println("Adding item to a buyer's shopping cart.");

        try {
            // Select buyer
            if (manager.getLogicalSizeBuyer() > 1) {
                printArrayBuyers(manager.getBuyers(), manager.getLogicalSizeBuyer());
                System.out.println("Please enter the index of the buyer you would like to add item for: ");
                indexBuyer = inputCheckIfValidInt(manager.getLogicalSizeBuyer(), sc);

                while (indexBuyer < 1 || indexBuyer > manager.getLogicalSizeBuyer()) {
                    System.out.println("The number of buyer doesn't exist, Please enter an existing number from the list: ");
                    printArrayBuyers(manager.getBuyers(), manager.getLogicalSizeBuyer());
                    System.out.println("Please enter the index of the buyer you would like to add item for: ");
                    indexBuyer = inputCheckIfValidInt(manager.getLogicalSizeBuyer(), sc);
                }
            } else if (manager.getLogicalSizeBuyer() == 1) {
                indexBuyer = 1;
                System.out.println("The buyer you would like to add item to is: " + "'" + manager.getBuyers()[0].getBuyerName() + "'");
            } else {
                System.out.println("There are no buyers in the platform. Please add a buyer first.");
                return;
            }
            buyerNumber = indexBuyer - 1;
            System.out.println("The chosen buyer is: " + "'" + manager.getBuyers()[buyerNumber].getBuyerName() + "'");

            if (manager.getLogicalSizeSeller() > 1) {
                printArraySellers(manager.getSellers(), manager.getLogicalSizeSeller());
                System.out.println("Please enter the index of the seller you would like to buy from: ");
                indexSeller = inputCheckIfValidInt(manager.getLogicalSizeSeller(), sc);

                while (indexSeller < 1 || indexSeller > manager.getLogicalSizeSeller()) {
                    System.out.println("The index of seller doesn't exist, Please enter an existing number from the list: ");
                    printArraySellers(manager.getSellers(), manager.getLogicalSizeSeller());
                    System.out.println("Please enter the index of the seller you would like to buy from: ");
                    indexSeller = inputCheckIfValidInt(manager.getLogicalSizeSeller(), sc);
                }
            } else if (manager.getLogicalSizeSeller() == 1) {
                indexSeller = 1;
                System.out.println("The (only one) Seller to buy from is: " + "'" + manager.getSellers()[0].getSellerName() + "'");
            } else {
                System.out.println("There are no sellers in the platform. Please add a seller first.");
                return;
            }
            sellerNumber = indexSeller - 1;
            System.out.println("The chosen seller to buy from is: " + "'" + manager.getSellers()[sellerNumber].getSellerName() + "'");

            int amountOfItems = manager.getSellers()[sellerNumber].getLogicalSizeItemSellerArray();
            if (amountOfItems <= 0) {
                System.out.println("There are no items this seller is currently selling...");
                System.out.println("Please add items to the seller's store first.");
                return;
            }
            manager.getSellers()[sellerNumber].printItems();
            System.out.println("Please choose the item you would like to purchase from the seller: ");
            indexItemFromSeller = inputCheckIfValidInt(amountOfItems, sc);

            while (indexItemFromSeller < 1 || indexItemFromSeller > amountOfItems) {
                System.out.println("The item number:" + indexItemFromSeller + " doesn't exist, Please enter an existing number from the list. ");
                manager.getSellers()[sellerNumber].printItems();
                System.out.println("Please choose the item you would like to purchase from the seller: ");
                indexItemFromSeller = inputCheckIfValidInt(amountOfItems, sc);
            }

            Item selectedItem = manager.getSellers()[sellerNumber].getItem(indexItemFromSeller - 1);
            String itemNameFromSeller = selectedItem.getName();
            double itemPriceFromSeller = selectedItem.getPrice();
            Item.Category category = selectedItem.getCategory();

            if (selectedItem instanceof DeluxItem) {
                DeluxItem deluxItem = (DeluxItem) selectedItem;
                manager.getBuyers()[buyerNumber].addItemDelux(itemNameFromSeller, itemPriceFromSeller, category, deluxItem.getDelux(), deluxItem.getExtraPrice());
            } else {
                manager.getBuyers()[buyerNumber].addItem(itemNameFromSeller, itemPriceFromSeller, category);
            }

            System.out.println("The item has been successfully added to " + "'" + manager.getBuyers()[buyerNumber].getBuyerName() + "'" + " buyer's shopping cart.");
            System.out.println("Here is the updated shopping cart:");
            manager.getBuyers()[buyerNumber].printItems();

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a valid number.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }


    public static void CheckingOutBuyersCart(ManagerClass manager, Scanner sc) {
        try {
            int index = 0;

            if (manager.getLogicalSizeBuyer() == 0) {
                System.out.println("There are no buyers in the store. Please add a buyer first.");
                return;
            }

            System.out.println("Checking out a buyer's cart.");
            printArrayBuyers(manager.getBuyers(), manager.getLogicalSizeBuyer());
            System.out.println("Please enter the number of the buyer you would like to check out for: ");
            index = inputCheckIfValidInt(manager.getLogicalSizeBuyer(), sc);

            while (index < 1 || index > manager.getLogicalSizeBuyer()) {
                System.out.println("Wrong buyer number. Please enter a valid number from the list: ");
                printArrayBuyers(manager.getBuyers(), manager.getLogicalSizeBuyer());
                index = inputCheckIfValidInt(manager.getLogicalSizeBuyer(), sc);
            }

            Buyers selectedBuyer = manager.getBuyers()[index - 1];

            if (selectedBuyer.getLogicalSizeItemsInCart() <= 0) {
                throw new IllegalStateException("There are no items in " + selectedBuyer.getBuyerName() + "'s shopping cart. Please add items before checking out.");
            } else {
                System.out.println("The chosen buyer to check out for is: " + selectedBuyer.getBuyerName());

                double payPrice = selectedBuyer.checkOutShoppingCart();

                selectedBuyer.todayDate();
                System.out.println("The total price of the shopping cart is: " + payPrice + " NIS");

                selectedBuyer.addShoppingCartHistory(payPrice);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a valid number.");
            sc.nextLine();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }




    public static void printBuyerData(ManagerClass manager, Scanner sc) {
        if (manager.getLogicalSizeBuyer() == 0) {
            System.out.println("There are no buyers in the store to show data ... Please add buyer first.");
        } else {
            System.out.println("Showing all buyers in the platform.");
            System.out.println(" ");
            printArrayBuyersData(manager.getBuyers(), manager.getLogicalSizeBuyer());

        }
    }

    public static void printSellerData(ManagerClass manager, Scanner sc) {
        if (manager.getLogicalSizeSeller() == 0) {
            System.out.println("There are no sellers in the store to show data, Please add seller first.");
        } else {
            System.out.println("Showing all seller's data.");
            printArraySellersData(manager.getSellers(), manager.getLogicalSizeSeller());
        }
    }


public static void printByCategory(ManagerClass manager, Scanner sc) {
    boolean isValidCategory = false;
    System.out.println("Please choose a category to see the items:");
    for (Item.Category cat : Item.Category.values()) {
        System.out.println(cat);
    }

    String categoryInput = sc.nextLine();
    categoryInput = capitalizeFirstLetter(categoryInput);
    Item.Category category = null;
    while (!isValidCategory) {
        try {
            category = Item.Category.valueOf(categoryInput);
            isValidCategory = true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Please enter a valid category from the list above: ");
            categoryInput = sc.nextLine();
            categoryInput = capitalizeFirstLetter(categoryInput);
        }
    }

    boolean itemsExistInCategory = manager.itemsExistInCategory(category);
    if (itemsExistInCategory) {
        manager.printItemsByCategory(category);
    } else {
        System.out.println("There are no items in the category: " + category);
    }
}


    public static void createShoppingCartFromHistory(ManagerClass manager, Scanner sc) {
        int indexBuyer;

        System.out.println("Creating new shopping cart from shopping cart history.");
        try {
            if (manager.getLogicalSizeBuyer() == 0) {
                throw new IllegalStateException("There are no buyers in the platform. \nExiting back to menu");
            }
            printArrayBuyers(manager.getBuyers(), manager.getLogicalSizeBuyer());
            System.out.print("Enter buyer index: ");
            indexBuyer = inputCheckIfValidInt(manager.getLogicalSizeBuyer(), sc);
            Buyers selectedBuyer = manager.getBuyers()[indexBuyer - 1];

            if (selectedBuyer.getLogicalSizeHistory() <= 0) {
                throw new IllegalStateException("The shopping cart history of " + selectedBuyer.getBuyerName() + " is empty. Please make a purchase first.");
            }
            if (selectedBuyer.getLogicalSizeItemsInCart() > 0) {
                System.out.println("Your current shopping cart has items. Do you want to keep the current items in the cart or replace the cart with a previous purchase cart from the purchase history?");
                System.out.println("For keeping the current shopping cart, please type '1'");
                System.out.println("For switching to the previous shopping cart from history, please type '2'");
                int SwitchOrKeepInput = inputCheckIfValidInt(2, sc); // logical size 2 because there are only 2 options to choose
                if (SwitchOrKeepInput == 1) {
                    System.out.println("Keeping the current cart and exiting to the menu.");
                    return;
                }
            }
            System.out.println("The chosen buyer is: " + "'" + selectedBuyer.getBuyerName() + "'");
            System.out.println("Please enter the cart you would like to add from your purchase history: ");
            selectedBuyer.printShoppingCartHistory();
            System.out.print("Your choice: ");
            int purchaseFromHistoryIndex = inputCheckIfValidInt(selectedBuyer.getLogicalSizeHistory(), sc);

            if (purchaseFromHistoryIndex <= 0 || purchaseFromHistoryIndex > selectedBuyer.getLogicalSizeHistory()) {
                throw new IllegalArgumentException("Invalid index for purchase history.");
            }

            PurchaseHistory selectedBuyerShoppingCart = selectedBuyer.getSpecificShoppingCartHistory(purchaseFromHistoryIndex - 1);

            ShoppingCart cartToClone = selectedBuyerShoppingCart.getShoppingCartByIndex(0);
            if (cartToClone == null) {
                throw new IllegalStateException("No shopping cart found in the history at the specified index.");
            }

            ShoppingCart newShoppingCart = (ShoppingCart) cartToClone.clone();
            selectedBuyer.setShoppingCart(newShoppingCart);
            System.out.println("New shopping cart created from history. Current items in the cart:");
            selectedBuyer.printItems();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer index.");
            sc.nextLine(); // Consume invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("Error: Cloning not supported for the shopping cart.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}



