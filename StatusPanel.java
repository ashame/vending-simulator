import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * @author nathan
 *         created on 2017-02-19.
 */
public class StatusPanel extends JPanel {
    private static JLabel balance = new JLabel();

    public StatusPanel(VendingMachine machine) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(getWidth(), 20));

        updateBalance(machine);

        add(balance);
    }

    public static void updateBalance(VendingMachine machine) {
        balance.setText(String.format("$%.2f", machine.getUserBalance()));
    }
}
