import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author nathan
 *         created on 2017-02-18.
 */
public class VendingMachine {
    private static final Logger LOGGER = Logger.getLogger(VendingMachine.class.getName());
    private double userBalance, machineBalance;
    private Map<Product, Integer> products;

    public VendingMachine() {
        userBalance = 0;
        machineBalance = 0;
        products = new HashMap<>();
    }

    public VendingMachine(Product... products) {
        this();
        for (Product product : products) {
            addProduct(product, 5);
        }
    }

    public void addMoney(Money money) {
        userBalance += money.value();
    }

    public void addProduct(Product product, int quantity) {
        if (!products.containsKey(product))
            products.put(product, quantity);
    }

    public double emptyMachine() {
        double bal = machineBalance;
        machineBalance = 0;
        return bal;
    }

    public Product getProductByName(String name) {
        for (Product p : products.keySet()) {
            if (normalize(p.getName()).equalsIgnoreCase(normalize(name)))
                return p;
        }
        return null;
    }

    public Object[] getProducts() {
        return products.keySet().toArray();
    }

    public int getStock(Product p) {
        return products.get(p);
    }

    public double getUserBalance() {
        return userBalance;
    }

    public String normalize(String str) {
        if (str.length() == 0)
            return "";

        String[] arr = str.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")) {
                res.append(arr[i]);
                if (i != arr.length - 1)
                    res.append(" ");
            }
        }

        return res.toString();
    }

    private boolean processCredit(double amount) {
        LOGGER.warning("Credit card payments are not supported yet.");
        return false;
    }

    public void restock(Product product, int quantity) {
        if (!products.containsKey(product)) {
            products.put(product, quantity);
        } else {
            if (products.get(product) < quantity)
                products.put(product, quantity);
        }
    }

    public void restockAll(int quantity) {
        for (Product product : products.keySet()) {
            if (products.get(product) < quantity)
                products.put(product, quantity);
        }
    }

    public void selectProduct(Product product) {
        if (userBalance == 0) {
            if (!processCredit(product.getPrice())) {
                LOGGER.warning("There was an error processing your payment.");
            }
        } else {
            if (product.getPrice() > userBalance) {
                LOGGER.warning("Not enough money!");
            } else {
                if (getStock(product) == 0) {
                    LOGGER.info(product.getName() + " is out of stock.");
                    return;
                }
                machineBalance += userBalance;
                userBalance = 0;
                updateStock(product, -1);
            }
        }
    }

    public void updateStock(Product product, int change) {
        if (products.containsKey(product)) {
            int qty = products.get(product) + change;
            products.put(product, qty);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VendingMachine[machineBalance: ");
        sb.append(String.format("%.2f", machineBalance));
        sb.append(", userBalance: ");
        sb.append(String.format("%.2f", userBalance));
        sb.append(", Products: ");
        for (Product p : products.keySet()) {
            sb.append("{");
            sb.append(p.getName());
            sb.append(": $");
            sb.append(String.format("%.2f", p.getPrice()));
            sb.append(", qty: ");
            sb.append(products.get(p));
            sb.append("}");
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }
}