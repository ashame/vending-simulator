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

import net.imashamed.vsim.commands.*;
import net.imashamed.vsim.swing.VendingGUI;

import javax.swing.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A GUI or command-line simulation of a vending machine.
 * @author nathan
 *         created on 2017-02-18.
 */
public class VendingMachineSimulation {
    private static HashMap<String, Command> commands = registerCommands();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean gui;

        if (args.length > 0) {
            gui = args[0].equalsIgnoreCase("--gui");
        } else {
            System.out.print("GUI (y/n): ");
            gui = in.nextLine().equalsIgnoreCase("y");
        }

        VendingMachine machine = new VendingMachine(
                new Product("Potato Chips", 1.25),
                new Product("Chocolate Bar", 1.50),
                new Product("Jell-O Cup", 1.50),
                new Product("Gummy Candies", 1.50),
                new Product("Cookies", 1.75),
                new Product("Granola Bar", 2.00),
                new Product("Mixed Nuts", 2.25),
                new Product("Dried Fruits", 3.00));

        if (gui) {
            in.close();
            VendingGUI vGUI = new VendingGUI(machine);
            SwingUtilities.invokeLater(() -> {
                vGUI.setVisible(true);
                vGUI.setAlwaysOnTop(true);
                vGUI.setAlwaysOnTop(false);
                vGUI.setLocationRelativeTo(null);
            });
        } else {
            commands.get("list").executeCommand(null, machine);
            processCommands(in, machine);
        }
    }

    /**
     * Processes commands from an input stream
     * @param in the input stream to read commands from
     * @param vm the {@link VendingMachine} to process commands to
     */
    private static void processCommands(Scanner in, VendingMachine vm) {
        String[] input;
        printCommands();
        do {
            System.out.print("\r\nEnter a command: ");
            input = in.nextLine().split(" ");
            Command cmd = commands.get(input[0]);
            if (cmd != null) {
                cmd.executeCommand(input, vm);
            } else {
                if (input[0].equals("quit"))
                    return;
                System.out.println("** " + String.join(" ", input) + " is not a valid command");
                printCommands();
            }
        } while (!input[0].equals("quit"));
    }

    /**
     * Registers {@link Command}s to be processed
     * @return a HashMap of all available commands
     */
    private static HashMap<String, Command> registerCommands() {
        HashMap<String, Command> commands = new HashMap<>();

        commands.put("add", new AddCommand());
        commands.put("list", new ListCommand());
        commands.put("select", new SelectCommand());
        commands.put("restock", new RestockCommand());
        commands.put("empty", new EmptyCommand());

        return commands;
    }

    /**
     * Prints all available {@link Command}s to console
     */
    private static void printCommands() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\nCommands: ");
        for (String s : commands.keySet()) {
            sb.append(s);
            sb.append(", ");
        }
        sb.append("quit");
        System.out.println(sb.toString());
    }
}