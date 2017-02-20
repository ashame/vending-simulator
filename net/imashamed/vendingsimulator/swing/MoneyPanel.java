package net.imashamed.vendingsimulator.swing;

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