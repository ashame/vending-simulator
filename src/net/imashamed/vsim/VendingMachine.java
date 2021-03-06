package net.imashamed.vsim;

/*
 *  This file is part of vending-simulator.
 *
 *  vending-simulator is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  vending-simulator is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with vending-simulator.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A vending machine object containing a collection of products
 * @author nathan
 *         created on 2017-02-18.
 */
public class VendingMachine {
    public static final int SUCCESS = 0;
    public static final int OUT_OF_STOCK = 1;
    public static final int PAYMENT_FAIL = 2;

    private static final Logger LOGGER = Logger.getLogger(VendingMachine.class.getName());
    private double userBalance, machineBalance;
    private Map<Product, Integer> products;

    /**
     * Constructs a VendingMachine with default values
     */
    public VendingMachine() {
        userBalance = 0;
        machineBalance = 0;
        products = new LinkedHashMap<>();
    }

    /**
     * Constructs a VendingMachine and fills it with given {@link Product}s with a default quantity of 5
     * @param products The {@link Product}s to fill the machine with
     */
    public VendingMachine(Product... products) {
        this();
        for (Product product : products) {
            addProduct(product, 5);
        }
    }

    /**
     * Adds a given unit of {@link Money} to <em>userBalance</em>
     * @param money the unit of {@link Money} to add
     */
    public void addMoney(Money money) {
        userBalance += money.value();
    }

    /**
     * Adds a given {@link Product} to the machine, and restocks it to a given <em>quantity</em>
     * @param product The {@link Product} to add
     * @param quantity The quantity to restock to
     */
    public void addProduct(Product product, int quantity) {
        if (!products.containsKey(product))
            products.put(product, quantity);
    }

    /**
     * Empties the machine balance
     * @return the machine balance
     */
    public double emptyMachine() {
        double bal = machineBalance;
        machineBalance = 0;
        return bal;
    }

    /**
     * Gets a given {@link Product} by a string name
     * @param name the name of the {@link Product}
     * @return the {@link Product} if found, or <em>null</em> if it doesn't exist
     */
    public Product getProductByName(String name) {
        for (Product p : products.keySet()) {
            if (normalize(p.getName()).equalsIgnoreCase(normalize(name)))
                return p;
        }
        return null;
    }

    /**
     * @return an Object array of all {@link Product}s in the machine
     */
    public Object[] getProducts() {
        return products.keySet().toArray();
    }

    /**
     * Gets the current stock of a given {@link Product}
     * @param p the product to check the stock for <b>precondition: </b>the {@link Product} must exist in the machine
     * @return the current stock of given {@link Product}
     */
    public int getStock(Product p) {
        return products.get(p);
    }

    /**
     * @return the current customer's balance
     */
    public double getUserBalance() {
        return userBalance;
    }

    /**
     * Normalizes a string - trims all excess whitespace before, after, and within the string
     * @param str the string to normalize
     * @return the normalized string
     */
    public static String normalize(String str) {
        if (str.length() == 0)
            return "";

        String[] arr = str.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")) {
                res.append(arr[i]);
                if (i != arr.length - 1)
                    res.append(" ");
            }
        }

        return res.toString();
    }

    /**
     * Processes a credit card payment of a given <em>amount</em>
     * @param amount the amount to charge to the card
     * @return if the payment was successful or not
     */
    //TODO: Credit payments
    private boolean processCredit(double amount) {
        LOGGER.warning("Credit card payments are not supported yet.");
        return false;
    }

    /**
     * Restocks a given {@link Product} to a given <em>quantity</em>. Adds the product if it does not already exist.
     * @param product the {@link Product} to restock
     * @param quantity the quantity to restock to
     */
    public void restock(Product product, int quantity) {
        if (!products.containsKey(product)) {
            products.put(product, quantity);
        } else {
            if (products.get(product) < quantity)
                products.put(product, quantity);
        }
    }

    /**
     * Restocks all {@link Product}s in the machine to a given <em>quantity</em>
     * @param quantity the quantity to restock to
     */
    public void restockAll(int quantity) {
        for (Product product : products.keySet()) {
            if (products.get(product) < quantity)
                products.put(product, quantity);
        }
    }

    /**
     * Selects a given {@link Product} from the machine, and processes payment
     * @param product the {@link Product} to purchase
     * @return the status of the payment
     */
    public int selectProduct(Product product) {
        if (getStock(product) == 0) {
            LOGGER.info(product.getName() + " is out of stock.");
            return OUT_OF_STOCK;
        } else {
            if (userBalance == 0) {
                if (!processCredit(product.getPrice())) {
                    return PAYMENT_FAIL;
                }
            } else if (product.getPrice() > userBalance) {
                LOGGER.warning("Not enough money!");
                return PAYMENT_FAIL;
            } else {
                machineBalance += userBalance;
                userBalance = 0;
                updateStock(product, -1);
            }
        }
        return SUCCESS;
    }

    /**
     * Updates the stock of a given {@link Product} by <em>change</em>
     * @param product the {@link Product} to update stock for
     * @param change the change in quantity
     */
    private void updateStock(Product product, int change) {
        if (products.containsKey(product)) {
            int qty = products.get(product) + change;
            products.put(product, qty);
        }
    }

    /**
     * @return a string representation of VendingMachine, in the format VendingMachine[machineBalance, userBalance, Products{productName, productPrice, productStock}]
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VendingMachine[machineBalance: ");
        sb.append(String.format("%.2f", machineBalance));
        sb.append(", userBalance: ");
        sb.append(String.format("%.2f", userBalance));
        sb.append(", Products: ");
        for (Product p : products.keySet()) {
            sb.append("{");
            sb.append(p.getName());
            sb.append(": $");
            sb.append(String.format("%.2f", p.getPrice()));
            sb.append(", qty: ");
            sb.append(products.get(p));
            sb.append("}");
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }
}