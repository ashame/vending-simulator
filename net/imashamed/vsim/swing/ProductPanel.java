package net.imashamed.vsim.swing;

import net.imashamed.vsim.Product;
import net.imashamed.vsim.VendingMachine;

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

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A JPanel containing all available {@link Product}s for the vending machine
 * @author nathan
 *         created on 2017-02-19.
 */
public class ProductPanel extends JPanel {
    private final static Logger LOGGER = Logger.getLogger(ProductPanel.class.getName());

    public ProductPanel(VendingMachine machine) {
        GridLayout productLayout = new GridLayout(8, 1);
        setLayout(productLayout);
        productLayout.setVgap(10);
        setBorder(new TitledBorder("Products"));

        Map<String, JButton> productButtons = new LinkedHashMap<>();
        for (Object o : machine.getProducts()) {
            Product p = (Product) o;
            productButtons.put(p.getName(), new JButton(p.getName()));
            productButtons.get(p.getName()).addActionListener(e -> {
                try {
                    switch (machine.selectProduct(p)) {
                        case VendingMachine.OUT_OF_STOCK:
                            StatusPanel.updateStatus(StatusPanel.ERROR_OUT_OF_STOCK);
                            break;
                        case VendingMachine.PAYMENT_FAIL:
                            StatusPanel.updateStatus(StatusPanel.ERROR_PAYMENT_FAIL);
                            break;
                        case VendingMachine.SUCCESS:
                            StatusPanel.updateStatus(StatusPanel.PAYMENT_SUCCESS);
                            break;
                        default:
                            StatusPanel.updateStatus(StatusPanel.ERROR_OTHER);
                    }
                } catch (UnsupportedOperationException ue) {
                    LOGGER.log(Level.WARNING, "Not yet implemented", ue);
                }
            });
        }

        for (JButton product : productButtons.values()) {
            add(product);
        }
    }
}