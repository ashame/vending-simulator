package net.imashamed.vsim.swing;

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

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A GUI for a vending machine simulation
 * @author nathan
 *         created on 2017-02-18.
 */
public class VendingGUI extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(VendingGUI.class.getName());

    public VendingGUI(VendingMachine machine) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while setting look and feel", e);
        }

        setTitle("Vending Machine Simulation");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        backPanel.add(new MoneyPanel(machine), BorderLayout.NORTH);
        backPanel.add(new ProductPanel(machine), BorderLayout.WEST);
        backPanel.add(new MachinePanel(), BorderLayout.CENTER);

        getContentPane().add(backPanel, BorderLayout.CENTER);
        getContentPane().add(new StatusPanel(machine), BorderLayout.SOUTH);

        setPreferredSize(new Dimension(380, 560));

        setResizable(false);
        pack();
    }
}