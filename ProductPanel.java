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