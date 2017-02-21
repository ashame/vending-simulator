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

import net.imashamed.vsim.VendingMachine;

/**
 * @author nathan
 *         created on 2017-02-20.
 */
public class EmptyCommand implements Command {

    @Override
    public void executeCommand(String[] cmd, VendingMachine vm) {
        System.out.printf("Emptied the machine for a total of $%.2f\r\n", vm.emptyMachine());
    }
}
