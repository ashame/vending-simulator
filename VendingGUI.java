import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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

        //Back panel
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        backPanel.add(new MoneyPanel(machine), BorderLayout.NORTH);
        backPanel.add(new ProductPanel(machine), BorderLayout.WEST);
        getContentPane().add(backPanel, BorderLayout.CENTER);
        getContentPane().add(new StatusPanel(machine), BorderLayout.SOUTH);

        setPreferredSize(new Dimension(480, 640));
        setResizable(false);
        pack();
    }
}