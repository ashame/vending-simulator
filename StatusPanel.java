import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author nathan
 *         created on 2017-02-19.
 */
public class StatusPanel extends JPanel {
    public static final int READY = 0;
    public static final int WORKING = 1;
    public static final int PAYMENT_SUCCESS = 2;
    public static final int ERROR_OUT_OF_STOCK = 3;
    public static final int ERROR_PAYMENT_FAIL = 4;
    public static final int ERROR_OTHER = 5;

    private static JLabel balance;
    private static StatusLabel statusLabel;
    private static int machineStatus = READY;

    public StatusPanel(VendingMachine vm) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(getWidth(), 20));

        balance = new JLabel("$0.00");
        statusLabel = new StatusLabel(vm);

        ActionListener statusListener = e -> repaint();
        new Timer(30, statusListener).start();
        new Thread(statusLabel).start();

        add(balance);
        add(Box.createHorizontalGlue());
        add(statusLabel);
        add(Box.createRigidArea(new Dimension(15, 0)));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D statusIndicator = new Ellipse2D.Double(getWidth() - 15, 4, 12, 12);
        Color c2 = g2.getColor();
        switch(machineStatus) {
            case READY:
                g2.setColor(Color.GREEN);
                break;
            case WORKING:
            case PAYMENT_SUCCESS:
                g2.setColor(Color.ORANGE);
                break;
            case ERROR_OUT_OF_STOCK:
            case ERROR_PAYMENT_FAIL:
            case ERROR_OTHER:
                g2.setColor(Color.RED);
                break;
            default:
               g2.setColor(Color.LIGHT_GRAY);
        }
        g2.draw(statusIndicator);
        g2.fill(statusIndicator);
        g2.setColor(c2);
    }

    public static void updateBalance(VendingMachine machine) {
        balance.setText(String.format("$%.2f", machine.getUserBalance()));
    }

    public static void updateStatus(int status) {
        statusLabel.updateStatus(status);
    }

    static class StatusLabel extends JLabel implements Runnable {
        private BlockingQueue<Integer> queue;
        private VendingMachine vm;

        public StatusLabel(VendingMachine vm) {
            this.vm = vm;
            this.queue = new LinkedBlockingQueue<>();
        }

        @Override
        public void run() {
            while (true) {
                long delay;
                try {
                    machineStatus = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (machineStatus) {
                    case PAYMENT_SUCCESS:
                        setText("VEND");
                        delay = 750;
                        break;
                    case ERROR_OUT_OF_STOCK:
                        setText("OUT OF STOCK");
                        delay = 1200;
                        break;
                    case ERROR_PAYMENT_FAIL:
                        setText("Not enough money");
                        delay = 1200;
                        break;
                    case ERROR_OTHER:
                        setText("ERROR");
                        delay = 1200;
                        break;
                    default:
                        setText("READY");
                        delay = 500;
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void updateStatus(int status) {
            queue.add(status);
        }
    }
}
