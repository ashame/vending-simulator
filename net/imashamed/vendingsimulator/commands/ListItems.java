package net.imashamed.vendingsimulator.commands;

import net.imashamed.vendingsimulator.Product;
import net.imashamed.vendingsimulator.VendingMachine;

/**
 * Prints out a list of all current items in the vending machine, along with its price and current stock
 * @author nathan
 *         created on 2017-02-18.
 */
public class ListItems implements Command {

    public void executeCommand(String[] cmd, VendingMachine vm) {
        System.out.printf("\r\n%-20s %-10s %5s\r\n", "net.imashamed.vendingsimulator.Product Name", "Price", "Stock");
        System.out.printf("%-20s %-10s %5s\r\n", "------------", "-----", "-----");
        for (Object o : vm.getProducts()) {
            Product p = (Product) o;
            System.out.printf("%-20s $%-10.2f %4d\r\n", p.getName(), p.getPrice(), vm.getStock(p));
        }
    }
}