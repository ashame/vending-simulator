import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

/**
 * @author nathan
 *         created on 2017-02-19.
 */
public class StatusPanel extends JPanel {
    public static final int READY = 0;
    public static final int WORKING = 1;
    public static final int ERROR = 2;
    public static final int TRANSITION = 3;

    private static JLabel balance = new JLabel();
    private static int machineStatus = 0;

    public StatusPanel(VendingMachine machine) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(getWidth(), 20));

        updateBalance(machine);

        ActionListener statusListener = e -> repaint();
        new Timer(100, statusListener).start();

        add(balance);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D e2d = new Ellipse2D.Double(getWidth() - 15, 4, 12, 12);
        Color c2 = g2.getColor();
        switch(machineStatus) {
            case READY:
                g2.setColor(Color.GREEN);
                break;
            case WORKING:
                g2.setColor(Color.ORANGE);
                break;
            case ERROR:
                g2.setColor(Color.RED);
                break;
            default:
               g2.setColor(Color.LIGHT_GRAY);
        }
        g2.draw(e2d);
        g2.fill(e2d);
        g2.setColor(c2);
    }

    public static void updateBalance(VendingMachine machine) {
        balance.setText(String.format("$%.2f", machine.getUserBalance()));
    }

    public static void setStatus(int status, long duration) {
        new Thread(() -> {
            long start = System.currentTimeMillis();
            machineStatus = status;
            while (System.currentTimeMillis() - start < duration) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (status != TRANSITION)
                setStatus(TRANSITION, 200);
            else
                machineStatus = READY;
        }).start();
    }
}
