package net.imashamed.vendingsimulator.commands;

import net.imashamed.vendingsimulator.VendingMachine;

/**
 * An interface for all command-line commands to be used
 * @author nathan
 *         created on 2017-02-18.
 */
public interface Command {
    void executeCommand(String[] cmd, VendingMachine vm);
}