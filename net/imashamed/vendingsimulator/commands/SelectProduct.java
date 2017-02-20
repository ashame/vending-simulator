package net.imashamed.vendingsimulator.commands;

import net.imashamed.vendingsimulator.Product;
import net.imashamed.vendingsimulator.VendingMachine;

/**
 * Selects a product to be purchased
 * @author nathan
 *         created on 2017-02-19.
 */
public class SelectProduct implements Command {

    public void executeCommand(String[] cmd, VendingMachine vm) {
        if (cmd.length < 2) {
            System.out.println("** Syntax: select [product name]");
            return;
        }

        String productName = "";
        for (int i = 1; i < cmd.length; i++) {
            productName += cmd[i];
            productName += " ";
        }
        productName = productName.trim();

        Product p = vm.getProductByName(productName);
        if (p != null) { //TODO: Implement credit support
            vm.selectProduct(p);
        } else {
            System.out.println("The product \"" + productName + "\" was not found.");
            System.out.println("Use 'list' to view all available products.");
        }
    }
}
