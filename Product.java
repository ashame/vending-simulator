import java.util.Objects;

/**
 * @author nathan
 *         created on 2017-02-18.
 */
public class Product {
    private final double price;
    private final String name;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Product) {
            Product product = (Product) o;
            if ((product.getPrice() == this.price) &&
                    (product.getName().equals(this.name))) {
                return true;
            }
        }
        return false;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Objects.hash(name, price);
    }

    public String toString() {
        return String.format("Product[%s, $%.2f]", name, price);
    }
}