import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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
                    machine.selectProduct(p);
                    StatusPanel.updateBalance(machine);
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
