package net.imashamed.vendingsimulator.swing;

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

import net.imashamed.vendingsimulator.Money;
import net.imashamed.vendingsimulator.VendingMachine;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A JPanel containing {@link Money} denominations to add to the machine
 * @author nathan
 *         created on 2017-02-19.
 */
public class MoneyPanel extends JPanel {
    public MoneyPanel(VendingMachine machine) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new TitledBorder("Add net.imashamed.vendingsimulator.Money"));

        Map<Money, JButton> moneyButtons = new LinkedHashMap<>();
        for (Money m : Money.values()) {
            moneyButtons.put(m, new JButton(String.format("$%.2f", m.value())));
            moneyButtons.get(m).addActionListener(e -> {
                machine.addMoney(m);
                StatusPanel.updateBalance(machine);
            });
        }

        add(Box.createHorizontalGlue());
        for (JButton money : moneyButtons.values()) {
            add(money);
            add(Box.createHorizontalGlue());
        }
    }
}