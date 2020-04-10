package net.imashamed.vsim.commands;

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

import net.imashamed.vsim.Product;
import net.imashamed.vsim.VendingMachine;

/**
 * Restocks a given product to a given amount
 * @author nathan
 *         created on 2017-02-19.
 */
public class RestockCommand implements Command {

    public void executeCommand(String[] cmd, VendingMachine vm) {
        if (cmd.length < 2) {
            System.out.println("** Syntax: restock [product] [quantity]");
            System.out.println("** Hint: use 'restock all [quantity]' to restock all products");
            return;
        }

        String productName = "";
        for (int i = 1; i < cmd.length - 1; i++) { //join string array to process
            productName += cmd[i];
            productName += " ";
        }
        productName = productName.trim();

        int quantity = 5; //default to 5 if no quantity / invalid quantity entered
        try {
            quantity = Integer.parseInt(cmd[cmd.length - 1]);
        } catch (NumberFormatException ignored) {}

        if (cmd[1].equalsIgnoreCase("all")) {
            vm.restockAll(quantity);
        } else {
            Product p = vm.getProductByName(productName);
            if (p != null)
                vm.restock(p, quantity);
            else
                System.out.println("\"" + productName + "\" does not exist in the vending machine.");
        }

    }
}
