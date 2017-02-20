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
 * Selects a product to be purchased
 * @author nathan
 *         created on 2017-02-19.
 */
public class SelectCommand implements Command {

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
